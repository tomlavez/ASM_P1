package cat.deim.asm_18.pedalean2.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cat.deim.asm_18.pedalean2.presentation.ui.theme.Padelean2Theme
import cat.deim.asm_18.pedalean2.presentation.viewmodels.BikeListState
import cat.deim.asm_18.pedalean2.presentation.viewmodels.BikeListViewModel

class BikeListActivity : ComponentActivity() {

    // Tal como no Login, a injeção do ViewModel será ativada no Android Studio
    private lateinit var viewModel: BikeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // viewModel = BikeListViewModel(GetBikesUsecase(BikeRepository(Datasource())))
        // viewModel.loadBikes() // Pede ao ViewModel para começar a carregar as bikes

        setContent {
            Padelean2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    
                    // Coleta o estado do ViewModel (comentado temporariamente)
                    // val uiState by viewModel.uiState.collectAsState()

                    // Código temporário para manter o VS Code sem erros críticos
                    BikeListScreen(
                        uiState = BikeListState.Loading, 
                        onProfileClick = {
                            // Quando o botão for clicado, abre o ecrã de Perfil
                            // val intent = Intent(this, ProfileActivity::class.java)
                            // startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}