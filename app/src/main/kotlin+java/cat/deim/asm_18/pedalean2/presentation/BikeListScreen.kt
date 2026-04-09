package cat.deim.asm_18.pedalean2.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.deim.asm_18.pedalean2.domain.models.Bike
import cat.deim.asm_18.pedalean2.presentation.viewmodels.BikeListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeListScreen(
    uiState: BikeListState,
    onProfileClick: () -> Unit
) {
    // Scaffold é um esqueleto de ecrã que já nos dá uma barra superior (TopAppBar) grátis
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bicicletas Disponíveis") },
                actions = {
                    Button(onClick = onProfileClick) {
                        Text("Perfil")
                    }
                }
            )
        }
    ) { paddingValues ->
        // O conteúdo principal do ecrã
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (uiState) {
                is BikeListState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is BikeListState.Error -> {
                    Text(
                        text = uiState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is BikeListState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.bikes) { bike ->
                            BikeItem(bike = bike)
                        }
                    }
                }
            }
        }
    }
}

// Composable secundário para desenhar cada carta de bicicleta na lista
@Composable
fun BikeItem(bike: Bike) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = bike.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Tipo: ${bike.type}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Bateria: ${bike.batteryLevel}%", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = if (bike.isRented) "Indisponível (Alugada)" else "Disponível", 
                style = MaterialTheme.typography.bodySmall,
                color = if (bike.isRented) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        }
    }
}