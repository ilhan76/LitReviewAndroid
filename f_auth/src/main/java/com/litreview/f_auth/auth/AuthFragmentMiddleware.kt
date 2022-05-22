package com.litreview.f_auth.auth

import com.litreview.base.util.EMPTY_STRING
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
                BackPressed::class react ::handleBackPressed,
                LoginClickedEvent::class eventToStream ::handleLoginClickedEvent,
                ValidationEvent.Request::class eventToEvent ::validateFields,
                ValidationEvent.Result::class.filter {
                    it.isSuccessful
                } react  { login() }
            )
        }
    }

    private fun login() {
        GlobalScope.launch {
            authInteractor.login(currentState.email, currentState.password).catch { e ->
                ch.showError.accept(e.message ?: EMPTY_STRING)
            }.collect {
                ch.openScreen.accept(authNavCommandProvider.toFeed)
            }
        }
    }

    private fun validateFields(event: ValidationEvent.Request): AuthFragmentEvent {
        return ValidationEvent.Result(
            event.requests.map {
                validator.validate(it)
            }
        )
    }

    private fun handleLoginClickedEvent(event: LoginClickedEvent): Flow<AuthFragmentEvent> {
        return flowOf(
            ValidationEvent.Request(
                listOf(
                    ValidationRequest(currentState.email, ValidationFieldType.EMAIL),
                    ValidationRequest(currentState.password, ValidationFieldType.PASSWORD)
                )
            )
        )
    }

    private fun handleBackPressed(event: BackPressed) {
        ch.openScreen.accept(authNavCommandProvider.toMain)
    }
}