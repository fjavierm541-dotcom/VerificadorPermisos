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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.munidanli.verificadorpermisos.screens.ManualSearchScreen
import com.munidanli.verificadorpermisos.ui.theme.VerificadorPermisosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VerificadorPermisosTheme {
                var pantallaActual by remember { mutableStateOf("inicio") }

                when (pantallaActual) {
                    "inicio" -> PantallaInicio(
                        irABusquedaManual = {
                            pantallaActual = "manual"
                        }
                    )

                    "manual" -> ManualSearchScreen(
                        volverInicio = {
                            pantallaActual = "inicio"
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(
    irABusquedaManual: () -> Unit
) {
    FondoGlass {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Verificador de Permisos",
                color = Color.White,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Municipalidad de Danlí",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(42.dp))

            GlassOptionCard(
                icon = "📷",
                title = "Escanear código QR",
                subtitle = "Verificación rápida del permiso",
                onClick = {
                    // Próximamente
                }
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
        }
    }
}

@Composable
fun FondoGlass(
    contenido: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF071A2F),
                        Color(0xFF0F3B6D),
                        Color(0xFF1E5A96)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .offset(x = (-60).dp, y = 40.dp)
                .blur(70.dp)
                .background(
                    Color(0xFF5EC8FF).copy(alpha = 0.35f),
                    RoundedCornerShape(120.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 80.dp, y = 80.dp)
                .blur(80.dp)
                .background(
                    Color(0xFF2563EB).copy(alpha = 0.35f),
                    RoundedCornerShape(140.dp)
                )
        )

        contenido()
    }
}

@Composable
fun GlassOptionCard(
    icon: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(26.dp),
                ambientColor = Color.Black.copy(alpha = 0.25f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.18f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.28f),
                            Color.White.copy(alpha = 0.10f)
                        )
                    )
                )
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        Color.White.copy(alpha = 0.25f),
                        RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 28.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 19.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = Color.White.copy(alpha = 0.78f),
                    fontSize = 14.sp
                )
            }
        }
    }
}