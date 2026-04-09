package cat.deim.asm_18.pedalean2.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.deim.asm_18.pedalean2.domain.usecase.Credentials
import cat.deim.asm_18.pedalean2.presentation.viewmodels.LoginState

@Composable
fun LoginScreen(
    loginState: LoginState,
    onLoginClick: (Credentials) -> Unit,
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Reage ao sucesso do login e avisa a Activity para mudar de tela
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onNavigateToHome()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pedalean2", style = MaterialTheme.typography.headlineLarge)
        
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (loginState is LoginState.Loading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { onLoginClick(Credentials(email, password)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }

        if (loginState is LoginState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = loginState.message, color = MaterialTheme.colorScheme.error)
        }
    }
}