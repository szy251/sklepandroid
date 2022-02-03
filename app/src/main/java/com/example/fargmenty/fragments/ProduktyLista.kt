package com.example.fargmenty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.adapters.ProduktAdapter
import com.example.fargmenty.data.models.handlers.getByKategoria
import com.example.fargmenty.data.models.handlers.getProductAll

class ProduktyLista:Fragment() {
    var kategoria = ""
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ProduktAdapter.viewHolderProdukt>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        val kategoriaRealm = arguments?.getString("kategoriaId")
        if(kategoriaRealm != null)
        {
            kategoria = kategoriaRealm
        }
        return inflater.inflate(R.layout.fragment_produktylista,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_viewProdukty= view.findViewById<RecyclerView>(R.id.recycler_viewProdukty)
        recycler_viewProdukty.apply {
           layoutManager= LinearLayoutManager(activity)
            if(kategoria == "") adapter= ProduktAdapter(getProductAll())
            else adapter= ProduktAdapter(getByKategoria(kategoria))
        }
    }
}