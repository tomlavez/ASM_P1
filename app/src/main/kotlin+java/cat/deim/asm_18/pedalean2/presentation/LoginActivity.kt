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
import cat.deim.asm_18.pedalean2.presentation.viewmodels.LoginViewModel
// Aqui você importará as dependências necessárias para injetar o ViewModel depois

class LoginActivity : ComponentActivity() {
    
    // NOTA: Como não estamos a usar Dagger/Hilt, teremos que instanciar o ViewModel com uma Factory depois.
    // Por enquanto, vamos deixar a estrutura montada.
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Instanciação manual temporária (será ajustada quando a injeção for configurada)
        // loginViewModel = LoginViewModel(LoginUsecase(UserRepository(Datasource())))

        setContent {
            Padelean2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    
                    // Coleta o estado do ViewModel
                    // val loginState by loginViewModel.loginState.collectAsState()
                    
                    // Código temporário para não dar erro no VS Code enquanto não temos a Factory
                    LoginScreen(
                        loginState = cat.deim.asm_18.pedalean2.presentation.viewmodels.LoginState.Idle,
                        onLoginClick = { credentials ->
                            // loginViewModel.performLogin(credentials)
                        },
                        onNavigateToHome = {
                            // Intent para abrir a próxima Activity
                            // val intent = Intent(this, BikeListActivity::class.java)
                            // startActivity(intent)
                            // finish()
                        }
                    )
                }
            }
        }
    }
}