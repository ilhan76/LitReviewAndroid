package com.litreview.f_auth.auth

import com.litreview.base.mvi.Request
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.base.validation.*
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class AuthFragmentMiddleware @Inject constructor(
    private val authNavCommandProvider: AuthNavCommandProvider,
    private val ch: AuthCommandHolder,
    private val state: FlowState<AuthState>,
    private val validator: FieldValidator,
    private val authInteractor: AuthInteractor
) : DslFlowMiddleware<AuthFragmentEvent> {

    private val currentState get() = state.currentState

    override fun transform(eventStream: Flow<AuthFragmentEvent>): Flow<AuthFragmentEvent> {
        return eventStream.transformations {
            addAll(
                LoginClickedEvent::class eventToStream { handleLoginClickedEvent() },
                ValidationEvent.Request::class eventToEvent ::handleValidationRequest,
                ValidationEvent.Result::class.filter {
                    it.isSuccessful
                } react { login() }
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

    private fun login() {
        GlobalScope.launch {
            authInteractor.login(currentState.email, currentState.password).catch { e ->
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
                        ch.openScreen.accept(authNavCommandProvider.toFeed)
                    }
                }
            }
        }
    }
}