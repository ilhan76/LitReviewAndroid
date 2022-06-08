package com.litreview.f_auth.register

import androidx.core.os.bundleOf
import com.litreview.base.analytics.APP_METRICA_AUTH
import com.litreview.base.analytics.APP_METRICA_REGISTER
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.base.validation.FieldValidator
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationRequest
import kotlinx.coroutines.flow.Flow
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import com.litreview.i_navigation.providers.RegisterNavCommandProvider
import com.yandex.metrica.YandexMetrica
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class RegisterFragmentMiddleware @Inject constructor(
    private val state: FlowState<RegisterState>,
    private val ch: RegisterCommandHolder,
    private val validator: FieldValidator,
    private val authInteractor: AuthInteractor,
    private val navCommandProvider: RegisterNavCommandProvider
) : DslFlowMiddleware<RegisterFragmentEvent> {

    private val currentState get() = state.currentState

    override fun transform(eventStream: Flow<RegisterFragmentEvent>): Flow<RegisterFragmentEvent> {
        return eventStream.transformations {
            addAll(
                RegisterBtnClickEvent::class eventToEvent { handleRegisterClick() },
                ValidationEvent.Request::class eventToEvent ::handleValidationRequest,
                ValidationEvent.Result::class.filter {
                    it.isSuccessful
                } eventToStream { register() }
            )
        }
    }

    private fun handleRegisterClick(): RegisterFragmentEvent {
        return ValidationEvent.Request(
            listOf(
                ValidationRequest(currentState.name, ValidationFieldType.NAME),
                ValidationRequest(currentState.secondName, ValidationFieldType.SECOND_NAME),
                ValidationRequest(currentState.email, ValidationFieldType.EMAIL),
                ValidationRequest(currentState.password, ValidationFieldType.PASSWORD),
                ValidationRequest(currentState.phone, ValidationFieldType.PHONE)
            )
        )
    }

    private fun handleValidationRequest(event: ValidationEvent.Request): RegisterFragmentEvent {
        return ValidationEvent.Result(
            event.requests.map {
                validator.validate(it)
            }
        )
    }

    private fun register(): Flow<RegisterFragmentEvent> = flow {
        try {
            val email = authInteractor.register(
                name = currentState.name,
                secondName = currentState.secondName,
                email = currentState.email,
                password = currentState.password,
                phone = currentState.phone
            )
            ch.openScreen.accept(
                navCommandProvider.toAuth(bundleOf(Args.EXTRA_FIRST to email))
            )

            YandexMetrica.reportEvent(APP_METRICA_REGISTER)
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }
}