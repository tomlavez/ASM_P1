package cat.deim.asm_18.pedalean2.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import cat.deim.asm_18.pedalean2.presentation.ui.theme.Padelean2Theme

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Padelean2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SplashScreen(
                        onTimeout = {
                            // Quando os 2.5 segundos passarem, abre o Login e fecha o Splash
                            // val intent = Intent(this, LoginActivity::class.java)
                            // startActivity(intent)
                            // finish()
                        }
                    )
                }
            }
        }
    }
}