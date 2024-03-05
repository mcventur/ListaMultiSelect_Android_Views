package com.mpd.pmdm.listamultiselect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mpd.pmdm.listamultiselect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ModulosListAdapter()
        binding.listModulos.adapter = adapter
        adapter.submitList(ModulosProvider.lista)

        //Activamos desactivamos el botón dependiendo de si hay modulos seleccionados o no
        adapter.hayModulosSeleccionados.observe(this){
            binding.button.isEnabled = it
        }

        binding.button.setOnClickListener {
            Log.d("MainActivity","Módulos seleccionados: ${adapter.modulosSeleccionados}")

            //Si queremos deseleccionar todos los elementos tras usar el listado para lo que necesitemos
            adapter.limpiarSeleccion()
        }
    }



}