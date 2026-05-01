package com.munidanli.verificadorpermisos.models

data class Permiso(
    val nopermiso: String,
    val propietario: String,
    val negocio: String,
    val ubicacion: String,
    val numrecibo: String,
    val periodo: String,
    val observacion: String,
    val validoHasta: String
)