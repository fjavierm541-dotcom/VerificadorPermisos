package com.munidanli.verificadorpermisos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.munidanli.verificadorpermisos.screens.HomeScreen
import com.munidanli.verificadorpermisos.screens.ManualSearchScreen
import com.munidanli.verificadorpermisos.ui.theme.VerificadorPermisosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VerificadorPermisosTheme {

                var pantallaActual by remember { mutableStateOf("inicio") }

                when (pantallaActual) {

                    "inicio" -> HomeScreen(
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