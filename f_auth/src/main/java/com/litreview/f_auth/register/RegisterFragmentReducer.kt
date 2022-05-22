package com.litreview.f_auth.register

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
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
    val phone: String = EMPTY_STRING
)

@Singleton
class RegisterCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val showErrorMessage = Command<String>()
}

class RegisterFragmentReducer @Inject constructor() : Reducer<RegisterFragmentEvent, RegisterState> {

    override fun reduce(state: RegisterState, event: RegisterFragmentEvent): RegisterState {
        return when(event) {
            is NameChangedEvent -> onNameChanged(state, event)
            is SecondNameChangedEvent -> onSecondNameChanged(state, event)
            is EmailChangedEvent -> onEmailChanged(state, event)
            is PasswordChangedEvent -> onPasswordChanged(state, event)
            is PhoneChangedEvent -> onPhoneChanged(state, event)
        }
    }

    private fun onNameChanged(
        state: RegisterState,
        event: NameChangedEvent
    ): RegisterState {
        return state.copy(
            name = event.value
        )
    }

    private fun onSecondNameChanged(
        state: RegisterState,
        event: SecondNameChangedEvent
    ): RegisterState {
        return state.copy(
            secondName = event.value
        )
    }

    private fun onEmailChanged(
        state: RegisterState,
        event: EmailChangedEvent
    ): RegisterState {
        return state.copy(
            email = event.value
        )
    }

    private fun onPasswordChanged(
        state: RegisterState,
        event: PasswordChangedEvent
    ): RegisterState {
        return state.copy(
            password = event.value
        )
    }

    private fun onPhoneChanged(
        state: RegisterState,
        event: PhoneChangedEvent
    ): RegisterState {
        return state.copy(
            phone = event.value
        )
    }
}