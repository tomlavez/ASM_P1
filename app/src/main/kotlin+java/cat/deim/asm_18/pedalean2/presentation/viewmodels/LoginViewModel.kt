package cat.deim.asm_18.pedalean2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import cat.deim.asm_18.pedalean2.domain.usecase.Credentials
import cat.deim.asm_18.pedalean2.domain.usecase.LoginUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Estados possíveis da tela de Login
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(
    private val loginUsecase: LoginUsecase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun performLogin(credentials: Credentials) {
        _loginState.value = LoginState.Loading
        
        val result = loginUsecase.execute(credentials)
        
        if (result.isSuccess) {
            _loginState.value = LoginState.Success
        } else {
            _loginState.value = LoginState.Error(result.exceptionOrNull()?.message ?: "Erro desconhecido")
        }
    }
}