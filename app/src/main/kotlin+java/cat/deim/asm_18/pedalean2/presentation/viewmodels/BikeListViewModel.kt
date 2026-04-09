package cat.deim.asm_18.pedalean2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import cat.deim.asm_18.pedalean2.domain.models.Bike
import cat.deim.asm_18.pedalean2.domain.usecase.GetBikesUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Os três estados possíveis da nossa tela de lista
sealed class BikeListState {
    object Loading : BikeListState()
    data class Success(val bikes: List<Bike>) : BikeListState()
    data class Error(val message: String) : BikeListState()
}

class BikeListViewModel(
    private val getBikesUsecase: GetBikesUsecase
) : ViewModel() {

    // Começamos o estado como "Loading" (Carregando)
    private val _uiState = MutableStateFlow<BikeListState>(BikeListState.Loading)
    val uiState: StateFlow<BikeListState> = _uiState.asStateFlow()

    fun loadBikes() {
        try {
            _uiState.value = BikeListState.Loading
            val bikes = getBikesUsecase.execute()
            _uiState.value = BikeListState.Success(bikes)
        } catch (e: Exception) {
            _uiState.value = BikeListState.Error(e.message ?: "Erro ao carregar as bicicletas")
        }
    }
}