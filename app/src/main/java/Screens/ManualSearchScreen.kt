package com.munidanli.verificadorpermisos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ManualSearchScreen(
    volverInicio: () -> Unit
) {
    var busqueda by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF071A2F),
                        Color(0xFF0F3B6D),
                        Color(0xFF1E5A96)
                    )
                )
            )
            .padding(22.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Búsqueda manual",
                color = Color.White,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ingrese número de permiso, negocio, propietario o recibo",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.18f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.28f),
                                    Color.White.copy(alpha = 0.10f)
                                )
                            )
                        )
                        .padding(18.dp)
                ) {
                    OutlinedTextField(
                        value = busqueda,
                        onValueChange = { busqueda = it },
                        label = { Text("Buscar permiso") },
                        placeholder = { Text("Ej. 4842, pulpería, propietario...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            resultado = if (busqueda.isBlank()) {
                                "Ingrese un dato para buscar."
                            } else {
                                "Buscando: $busqueda"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text("BUSCAR", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            if (resultado.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.18f)
                    )
                ) {
                    Text(
                        text = resultado,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            TextButton(
                onClick = { volverInicio() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Volver al inicio", color = Color.White)
            }
        }
    }
}