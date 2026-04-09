package cat.deim.asm_18.pedalean2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cat.deim.asm_18.pedalean2.presentation.ui.theme.Padelean2Theme
import cat.deim.asm_18.pedalean2.presentation.viewmodels.ProfileState
import cat.deim.asm_18.pedalean2.presentation.viewmodels.ProfileViewModel

class ProfileActivity : ComponentActivity() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // viewModel = ProfileViewModel(GetUserProfileUsecase(UserRepository(Datasource())))
        // viewModel.loadProfile()

        setContent {
            Padelean2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    
                    // val uiState by viewModel.uiState.collectAsState()
                    
                    // Passamos um estado temporário de Loading
                    ProfileScreen(
                        uiState = ProfileState.Loading,
                        onBackClick = {
                            // finish() // Fecha esta Activity e volta para a lista de bikes
                        }
                    )
                }
            }
        }
    }
}