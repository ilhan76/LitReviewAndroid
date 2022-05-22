package com.litreview.f_auth.register

import com.litreview.base.validation.FieldValidator
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationRequest
import kotlinx.coroutines.flow.Flow
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import com.litreview.i_auth.AuthInteractor
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class RegisterFragmentMiddleware @Inject constructor(
    private val state: FlowState<RegisterState>,
    private val validator: FieldValidator,
    private val authInteractor: AuthInteractor
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

    private fun register() {}
}