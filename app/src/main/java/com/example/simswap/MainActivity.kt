package com.example.simswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simswap.ui.theme.SIMSWAPTheme
import com.example.simswapsimulation.ui.theme.SimSwapSimulationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimSwapSimulationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SimSwapScreen()
                }
            }
        }
    }
}

@Composable
fun SimSwapScreen() {
    var phoneNumber by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Simulação de SIM Swap", style = MaterialTheme.typography.titleLarge)

        // Campo para número de telefone
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Número de Telefone") },
            placeholder = { Text("Digite o número (ex: 11987654321)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão para simular consulta
        Button(
            onClick = {
                resultMessage = verificarSimSwap(phoneNumber)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verificar Troca de Chip")
        }

        // Exibir resultado
        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                style = MaterialTheme.typography.bodyLarge,
                color = if (resultMessage.contains("Sim Swap detectado")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        }
    }
}

// Função que simula a verificação de SIM Swap
fun verificarSimSwap(phoneNumber: String): String {
    // Simulação de resposta da operadora com base em um critério fictício
    if (phoneNumber.isBlank()) {
        return "Por favor, insira um número de telefone válido."
    }

    // Simular respostas aleatórias
    return if (Math.random() > 0.5) {
        "Sim Swap detectado: O chip foi trocado recentemente."
    } else {
        "Nenhuma troca de chip detectada."
    }
}
