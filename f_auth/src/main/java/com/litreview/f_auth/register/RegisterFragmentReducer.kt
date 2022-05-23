package com.litreview.f_auth.register

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationResult
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import javax.inject.Inject
import javax.inject.Singleton

data class RegisterState(
    val name: String = EMPTY_STRING,
    val secondName: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING,

    val nameValidationResult: ValidationResult? = null,
    val secondNameValidationResult: ValidationResult? = null,
    val emailValidationResult: ValidationResult? = null,
    val passwordValidationResult: ValidationResult? = null,
    val phoneValidationResult: ValidationResult? = null
)

@Singleton
class RegisterCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val showErrorMessage = Command<String>()
}

class RegisterFragmentReducer @Inject constructor() : Reducer<RegisterFragmentEvent, RegisterState> {

    override fun reduce(state: RegisterState, event: RegisterFragmentEvent): RegisterState {
        return when(event) {
            is NameChangedEvent -> state.copy(name = event.value)
            is SecondNameChangedEvent -> state.copy(secondName = event.value)
            is EmailChangedEvent -> state.copy(email = event.value)
            is PasswordChangedEvent -> state.copy(password = event.value)
            is PhoneChangedEvent -> state.copy(phone = event.value)
            is ValidationEvent.Result -> onValidationResult(event, state)
            else -> state
        }
    }

    private fun onValidationResult(
        event: ValidationEvent.Result,
        state: RegisterState
    ): RegisterState {
        var nameValidationResult = state.nameValidationResult
        var secondNameValidationResult = state.secondNameValidationResult
        var emailValidationResult = state.emailValidationResult
        var passwordValidationResult = state.passwordValidationResult
        var phoneValidationResult = state.phoneValidationResult

        event.resultList.forEach { result ->
            when (result.fieldType) {
                ValidationFieldType.EMAIL -> emailValidationResult = result
                ValidationFieldType.PASSWORD -> passwordValidationResult = result
                ValidationFieldType.PHONE -> phoneValidationResult = result
                ValidationFieldType.NAME -> nameValidationResult = result
                ValidationFieldType.SECOND_NAME -> secondNameValidationResult = result
                else -> {
                    // do nothing
                }
            }
        }
        return state.copy(
            nameValidationResult = nameValidationResult,
            secondNameValidationResult = secondNameValidationResult,
            emailValidationResult = emailValidationResult,
            passwordValidationResult = passwordValidationResult,
            phoneValidationResult = phoneValidationResult
        )
    }
}