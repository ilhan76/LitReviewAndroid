package com.litreview.f_auth.register

import android.os.Bundle
import com.litreview.base.mvi.Request
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.base.validation.FieldValidator
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationRequest
import kotlinx.coroutines.flow.Flow
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import com.litreview.i_navigation.providers.RegisterNavCommandProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
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
                } react { register() }
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

    private fun register() {
        GlobalScope.launch {
            authInteractor.register(
                name = currentState.name,
                secondName = currentState.secondName,
                email = currentState.email,
                password = currentState.password,
                phone = currentState.phone
            ).catch { e ->
                ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
            }.collect {
                //todo - придумать как здесь сделать через RequestEvent
                when (it) {
                    is Request.Loading -> {
                        // todo Будет сделано, как продумаю RequestEvent
                    }
                    is Request.Error -> {
                        ch.showErrorMessage.accept(it.getErrorOrNull()?.message ?: DEFAULT_ERROR)
                    }
                    is Request.Success -> {
                        ch.openScreen.accept(navCommandProvider.toAuth(Bundle().apply {
                            putString(
                                Args.EXTRA_FIRST,
                                it.getData()
                            )
                        }))
                    }
                }
            }
        }
    }
}