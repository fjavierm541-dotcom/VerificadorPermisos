package com.munidanli.verificadorpermisos.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.munidanli.verificadorpermisos.components.FondoGlass
import com.munidanli.verificadorpermisos.components.GlassOptionCard

@Composable
fun HomeScreen(
    irABusquedaManual: () -> Unit
) {
    var mostrarAcercaDe by remember { mutableStateOf(false) }

    FondoGlass {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Verificador PO",
                color = Color.White,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Alcaldía Municipal de Danlí",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(42.dp))

            GlassOptionCard(
                icon = "📷",
                title = "Escanear código QR",
                subtitle = "Función disponible próximamente",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            GlassOptionCard(
                icon = "🔎",
                title = "Búsqueda manual",
                subtitle = "Ingresar datos del permiso",
                onClick = {
                    irABusquedaManual()
                }
            )

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                text = "Acerca de",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 13.sp,
                modifier = Modifier.clickable {
                    mostrarAcercaDe = true
                }
            )
        }

        if (mostrarAcercaDe) {
            AlertDialog(
                onDismissRequest = {
                    mostrarAcercaDe = false
                },
                title = {
                    Text("Verificador PO")
                },
                text = {
                    Text(
                        """
                        Alcaldía Municipal de Danlí
                        
                        Aplicación móvil para la consulta local de permisos de operación vigentes.
                        
                        Versión: 1.0.0
                        Fecha de creación: Mayo 2026
                        Desarrollado por: F. Javier Medina
                        """.trimIndent()
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarAcercaDe = false
                        }
                    ) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}