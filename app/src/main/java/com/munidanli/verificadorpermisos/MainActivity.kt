package com.munidanli.verificadorpermisos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.munidanli.verificadorpermisos.ui.theme.VerificadorPermisosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerificadorPermisosTheme {
                PantallaInicio()
            }
        }
    }
}

@Composable
fun PantallaInicio() {

    // Fondo degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F172A),
                        Color(0xFF1E3A8A)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Verificador de Permisos",
                color = Color.White,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            // CARD QR
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(10.dp, RoundedCornerShape(20.dp))
                    .clickable {
                        // Aquí irá el QR después
                    },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Escanear código QR", fontSize = 18.sp)
                    Text("Verificación rápida", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // CARD MANUAL
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(10.dp, RoundedCornerShape(20.dp))
                    .clickable {
                        // Navegación luego
                    },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Búsqueda manual", fontSize = 18.sp)
                    Text("Ingresar datos del permiso", color = Color.Gray)
                }
            }
        }
    }
}



