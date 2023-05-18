package com.example.sharedpreferences

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var nom = "Sara Martinez"
    private var carrera = "Informatica"
    private var tel = "3741158131"
    lateinit var txtNombre: TextView
    lateinit var txtCarrera: TextView
    lateinit var txtCelular: TextView
    lateinit var btnEdit: Button

    companion object {
        private const val REQUEST_CODE_EDIT = 1
    }

    private lateinit var editLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCarrera = findViewById(R.id.txtCarrera)
        txtCelular = findViewById(R.id.txtCelular)
        txtNombre = findViewById(R.id.txtNombre)
        btnEdit = findViewById(R.id.btnEdit)

        loadData()

        btnEdit.setOnClickListener {
            abrirEdicion()
        }

        editLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                    val nuevoNombre = result.data!!.getStringExtra("extra_nombre")
                    val nuevaCarrera = result.data!!.getStringExtra("extra_carrera")
                    val nuevoTelefono = result.data!!.getStringExtra("extra_telefono")

                    if (!nuevoNombre.isNullOrEmpty()) {
                        nom = nuevoNombre
                    }

                    if (!nuevaCarrera.isNullOrEmpty()) {
                        carrera = nuevaCarrera
                    }

                    if (!nuevoTelefono.isNullOrEmpty()) {
                        tel = nuevoTelefono
                    }

                    displayUserData()
                }
            }
    }

    private fun loadData() {
        val data = SharedPrefManager.loadData(this)
        nom = data.first ?: nom
        carrera = data.second ?: carrera
        tel = data.third ?: tel
        displayUserData()
    }

    private fun displayUserData() {
        txtNombre.text = nom
        txtCarrera.text = carrera
        txtCelular.text = tel


    }

    private fun abrirEdicion() {
        val intent = Edicion.newIntent(this, nom, carrera, tel)
        editLauncher.launch(intent)
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    private fun saveData() {
        SharedPrefManager.saveData(this, nom, carrera, tel)
    }
}