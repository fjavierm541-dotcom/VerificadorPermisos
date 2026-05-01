package com.munidanli.verificadorpermisos.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.munidanli.verificadorpermisos.models.Permiso
import java.io.File
import java.io.FileOutputStream

class DatabaseHelper(private val context: Context) {

    private val databaseName = "permisos2026.db"

    private fun getDatabasePath(): File {
        return context.getDatabasePath(databaseName)
    }

    fun abrirBaseDatos(): SQLiteDatabase {
        val dbFile = getDatabasePath()

        if (!dbFile.exists()) {
            copiarBaseDesdeAssets(dbFile)
        }

        return SQLiteDatabase.openDatabase(
            dbFile.path,
            null,
            SQLiteDatabase.OPEN_READONLY
        )
    }

    private fun copiarBaseDesdeAssets(dbFile: File) {
        dbFile.parentFile?.mkdirs()

        context.assets.open(databaseName).use { input ->
            FileOutputStream(dbFile).use { output ->
                input.copyTo(output)
            }
        }
    }

    fun buscarPermiso(texto: String): Permiso? {
        val db = abrirBaseDatos()
        val busqueda = "%${texto.trim()}%"

        val cursor = db.rawQuery(
            """
            SELECT nopermiso, propietario, negocio, ubicacion, numrecibo, periodo, observacion, valido_hasta
            FROM permisos
            WHERE nopermiso LIKE ?
               OR propietario LIKE ?
               OR negocio LIKE ?
               OR numrecibo LIKE ?
            LIMIT 1
            """.trimIndent(),
            arrayOf(busqueda, busqueda, busqueda, busqueda)
        )

        var permiso: Permiso? = null

        if (cursor.moveToFirst()) {
            permiso = Permiso(
                nopermiso = cursor.getString(0) ?: "",
                propietario = cursor.getString(1) ?: "",
                negocio = cursor.getString(2) ?: "",
                ubicacion = cursor.getString(3) ?: "",
                numrecibo = cursor.getString(4) ?: "",
                periodo = cursor.getString(5) ?: "",
                observacion = cursor.getString(6) ?: "",
                validoHasta = cursor.getString(7) ?: ""
            )
        }

        cursor.close()
        db.close()

        return permiso
    }
}