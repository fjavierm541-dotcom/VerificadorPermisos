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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.munidanli.verificadorpermisos.data.DatabaseHelper
import com.munidanli.verificadorpermisos.models.Permiso
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun ManualSearchScreen(
    volverInicio: () -> Unit
) {
    val context = LocalContext.current
    val databaseHelper = remember { DatabaseHelper(context) }

    var busqueda by remember { mutableStateOf("") }
    var permisoEncontrado by remember { mutableStateOf<Permiso?>(null) }
    var mensaje by remember { mutableStateOf("") }

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                    containerColor = Color.White.copy(alpha = 0.32f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.42f),
                                    Color.White.copy(alpha = 0.22f)
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
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White.copy(alpha = 0.85f),
                            focusedPlaceholderColor = Color.White.copy(alpha = 0.70f),
                            unfocusedPlaceholderColor = Color.White.copy(alpha = 0.65f),
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.65f),
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (busqueda.isBlank()) {
                                permisoEncontrado = null
                                mensaje = "⚠️ Ingrese un dato para buscar."
                            } else {
                                val resultado = databaseHelper.buscarPermiso(busqueda)

                                if (resultado != null) {
                                    permisoEncontrado = resultado
                                    mensaje = ""
                                } else {
                                    permisoEncontrado = null
                                    mensaje = "❌ Permiso no encontrado."
                                }
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

            if (mensaje.isNotEmpty()) {
                CardResultadoMensaje(mensaje)
            }

            permisoEncontrado?.let { permiso ->
                CardResultadoPermiso(permiso)
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

@Composable
fun CardResultadoMensaje(
    mensaje: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.18f)
        )
    ) {
        Text(
            text = mensaje,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(18.dp)
        )
    }
}

@Composable
fun CardResultadoPermiso(
    permiso: Permiso
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.18f)
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "✅ PERMISO ENCONTRADO",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text("No. Permiso: ${permiso.nopermiso}", color = Color.White)
            Text("Negocio: ${permiso.negocio}", color = Color.White)
            Text("Propietario: ${permiso.propietario}", color = Color.White)
            Text("Ubicación: ${permiso.ubicacion}", color = Color.White)
            Text("No. Recibo: ${permiso.numrecibo}", color = Color.White)
            Text("Periodo: ${permiso.periodo}", color = Color.White)
            Text("Observación: ${permiso.observacion}", color = Color.White)
            Text("Válido hasta: ${permiso.validoHasta}", color = Color.White)
        }
    }
}