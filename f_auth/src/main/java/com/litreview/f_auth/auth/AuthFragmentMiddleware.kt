package com.litreview.f_auth.auth

import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.base.validation.*
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import com.litreview.i_profile.ProfileInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class AuthFragmentMiddleware @Inject constructor(
    private val authNavCommandProvider: AuthNavCommandProvider,
    private val ch: AuthCommandHolder,
    private val state: FlowState<AuthState>,
    private val validator: FieldValidator,
    private val authInteractor: AuthInteractor,
    private val profileInteractor: ProfileInteractor
) : DslFlowMiddleware<AuthFragmentEvent> {

    private val currentState get() = state.currentState

    override fun transform(eventStream: Flow<AuthFragmentEvent>): Flow<AuthFragmentEvent> {
        return eventStream.transformations {
            addAll(
                LoginClickedEvent::class eventToStream { handleLoginClickedEvent() },
                ValidationEvent.Request::class eventToEvent ::handleValidationRequest,
                ValidationEvent.Result::class.filter {
                    it.isSuccessful
                } eventToStream { login() }
            )
        }
    }

    private fun handleLoginClickedEvent(): Flow<AuthFragmentEvent> {
        return flowOf(
            ValidationEvent.Request(
                listOf(
                    ValidationRequest(currentState.email, ValidationFieldType.EMAIL),
                    ValidationRequest(currentState.password, ValidationFieldType.PASSWORD)
                )
            )
        )
    }

    private fun handleValidationRequest(event: ValidationEvent.Request): AuthFragmentEvent {
        return ValidationEvent.Result(
            event.requests.map {
                validator.validate(it)
            }
        )
    }

    private fun login(): Flow<AuthFragmentEvent> = flow {
        try {
            authInteractor.login(currentState.email, currentState.password)
            profileInteractor.getAndSaveUserInfo()
            ch.openScreen.accept(authNavCommandProvider.toFeed)
        } catch (e: Throwable) {
            //todo - нормальная обработка
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }
}