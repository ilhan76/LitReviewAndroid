package com.litreview.f_auth.register

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class RegisterState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
)

@Singleton
class RegisterCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val showErrorMessage = Command<String>()
}

class RegisterFragmentReducer @Inject constructor() : Reducer<RegisterFragmentEvent, RegisterState> {

    override fun reduce(state: RegisterState, event: RegisterFragmentEvent): RegisterState {
        TODO("Not yet implemented")
    }
}