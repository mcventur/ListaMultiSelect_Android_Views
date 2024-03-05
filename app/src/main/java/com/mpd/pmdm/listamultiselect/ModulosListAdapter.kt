package com.mpd.pmdm.listamultiselect

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mpd.pmdm.listamultiselect.databinding.ItemModuloBinding


class ModulosListAdapter() :
    ListAdapter<Modulo, ModulosListAdapter.ViewHolder>(ModuloDiffCallback) {

    //Conjunto (Set) de elementos seleccionados de la lista
    private val _modulosSeleccionados = mutableSetOf<Modulo>()
    val modulosSeleccionados: Set<Modulo> = _modulosSeleccionados
    //Un observable booleano para activar/desactivar controles en la vista en función de si hay elementos seleccionados o no
    private val _hayModulosSeleccionados = MutableLiveData<Boolean>(false)
    val hayModulosSeleccionados :LiveData<Boolean> = _hayModulosSeleccionados



    //Escribimos nuestra implementación de Diffutil.Itemcallback
    companion object {
        private val ModuloDiffCallback = object : DiffUtil.ItemCallback<Modulo>() {
            override fun areContentsTheSame(oldItem: Modulo, newItem: Modulo): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Modulo, newItem: Modulo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemModuloBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(val binding: ItemModuloBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var selected: Boolean = false
        fun bind(modulo: Modulo) {
            //Ojo. La vista debe estar en un MaterialCardView
            val card = itemView as MaterialCardView
            //Al cargar o recargar la lista, todos sin seleccionar
            card.isChecked = false

            binding.IdModulo.text = modulo.id.toString()
            binding.nombreModulo.text = modulo.nombre

            itemView.setOnClickListener {
                //Marcamos/Desmarcamos el item
                card.isChecked = !card.isChecked
                //Añadimos o sacamos del conjunto de módulos seleccionados
                if (modulo in modulosSeleccionados) _modulosSeleccionados.remove(modulo)
                else _modulosSeleccionados.add(modulo)
                _hayModulosSeleccionados.value = modulosSeleccionados.isNotEmpty()
            }
        }
    }

    /**
     * Si interesa borrar la selección, se puede hacer de este modo
     */
     fun limpiarSeleccion(){
         _modulosSeleccionados.clear()
         _hayModulosSeleccionados.value = false
         notifyDataSetChanged()
     }

}