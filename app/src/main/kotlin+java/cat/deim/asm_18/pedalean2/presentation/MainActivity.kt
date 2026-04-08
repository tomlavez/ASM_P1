package cat.deim.asm_18.pedalean2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cat.deim.asm_18.pedalean2.presentation.ui.theme.Padelean2Theme

class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Log.d(TAG, "Before enableEdgeToEdge")

        // Using fullscreen
        enableEdgeToEdge()
        
        Log.d(TAG, "Before setContent")
        
        setContent {
            Padelean2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        MainScreen(TAG)
                    }
                }
            }
        }

        Log.d(TAG, "After setContent Execution")
    }
}

@Composable
fun MainScreen(tag: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text
        GreetingText("Android")

        Spacer(modifier = Modifier.height(16.dp))

        //Button
        Button(
            onClick = {
                Log.d(tag, "Button clicked")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00008B)
            )
        ) {
            Text(text = "Clique aqui", color = Color.White)
        }
    }
}

@Composable
fun GreetingText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
