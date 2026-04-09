package cat.deim.asm_18.pedalean2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import cat.deim.asm_18.pedalean2.domain.models.User
import cat.deim.asm_18.pedalean2.domain.usecase.GetUserProfileUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val user: User) : ProfileState()
    data class Error(val message: String) : ProfileState()
}

class ProfileViewModel(
    private val getUserProfileUsecase: GetUserProfileUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    fun loadProfile() {
        val result = getUserProfileUsecase.execute()
        if (result.isSuccess) {
            _uiState.value = ProfileState.Success(result.getOrNull()!!)
        } else {
            _uiState.value = ProfileState.Error(result.exceptionOrNull()?.message ?: "Erro ao carregar o perfil.")
        }
    }
}