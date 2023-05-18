package com.example.sharedpreferences

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Edicion : AppCompatActivity() {
    companion object {
        private const val EXTRA_NOMBRE = "extra_nombre"
        private const val EXTRA_CARRERA = "extra_carrera"
        private const val EXTRA_TELEFONO = "extra_telefono"
        lateinit var edtxtNombre: EditText
        lateinit var edtxtCarrera: EditText
        lateinit var edtxtTelefono: EditText
        lateinit var btnEditar: Button

        fun newIntent(context: Context, nombre: String, carrera: String, telefono: String): Intent {
            val intent = Intent(context, Edicion::class.java)
            intent.putExtra(EXTRA_NOMBRE, nombre)
            intent.putExtra(EXTRA_CARRERA, carrera)
            intent.putExtra(EXTRA_TELEFONO, telefono)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion)
        val nombre = intent.getStringExtra(EXTRA_NOMBRE)
        val carrera = intent.getStringExtra(EXTRA_CARRERA)
        val telefono = intent.getStringExtra(EXTRA_TELEFONO)
        edtxtNombre = findViewById(R.id.edtextNombre)
        edtxtCarrera = findViewById(R.id.textedCarrera)
        edtxtTelefono = findViewById(R.id.textedTelefono)
        btnEditar = findViewById(R.id.btnEditar)

        edtxtNombre.setText(nombre)
        edtxtCarrera.setText(carrera)
        edtxtTelefono.setText(telefono)

        btnEditar.setOnClickListener {
            val nuevoNombre = edtxtNombre.text.toString()
            val nuevaCarrera = edtxtCarrera.text.toString()
            val nuevoTelefono = edtxtTelefono.text.toString()

            val intent = Intent()
            intent.putExtra(EXTRA_NOMBRE, nuevoNombre)
            intent.putExtra(EXTRA_CARRERA, nuevaCarrera)
            intent.putExtra(EXTRA_TELEFONO, nuevoTelefono)
            setResult(Activity.RESULT_OK, intent)
            saveData(nuevoNombre, nuevaCarrera, nuevoTelefono)
            finish()
        }
    }

    private fun saveData(nombre: String, carrera: String, telefono: String) {
        SharedPrefManager.saveData(this, nombre, carrera, telefono)
    }
}