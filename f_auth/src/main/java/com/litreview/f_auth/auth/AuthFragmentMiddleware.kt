package com.litreview.f_auth.auth

import com.litreview.base.validation.FieldValidator
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationRequest
import com.litreview.base.validation.ValidationResult
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class AuthFragmentMiddleware @Inject constructor(
    private val authNavCommandProvider: AuthNavCommandProvider,
    private val ch: AuthCommandHolder,
    private val state: FlowState<AuthState>,
    private val validator: FieldValidator,
    private val authInteractor: AuthInteractor
): DslFlowMiddleware<AuthFragmentEvent> {

    override fun transform(eventStream: Flow<AuthFragmentEvent>): Flow<AuthFragmentEvent> {
        return eventStream.transformations {
            addAll(
                BackPressed::class react ::handleBackPressed,
                LoginClickedEvent::class eventToStream ::handleLoginClickedEvent,
                Validation::class react ::validateFields
            )
        }
    }

    //todo - сделать более чисто
    private fun validateFields(event: Validation) {
        val emailValidationResult = validator.validate(
            ValidationRequest(event.email, ValidationFieldType.EMAIL)
        )
        if (emailValidationResult is ValidationResult.Failure) {
            ch.showEmailValidationError.accept(emailValidationResult.messageRes)
        }
        val passwordValidationResult = validator.validate(
            ValidationRequest(event.password, ValidationFieldType.PASSWORD)
        )
        if (passwordValidationResult is ValidationResult.Failure) {
            ch.showPasswordValidationError.accept(passwordValidationResult.messageRes)
        }
        if (emailValidationResult is ValidationResult.Success && passwordValidationResult is ValidationResult.Success){
            ch.openScreen.accept(authNavCommandProvider.toFeed)
        }
    }

    private fun handleLoginClickedEvent(event: LoginClickedEvent) : Flow<AuthFragmentEvent> {
        return flowOf(Validation(state.currentState.email, state.currentState.password))
    }

    private fun handleBackPressed(event: BackPressed) {
        ch.openScreen.accept(authNavCommandProvider.toMain)
    }
}