package com.example.fargmenty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.adapters.KategoriaAdapter
import com.example.fargmenty.data.adapters.ProduktAdapter
import com.example.fargmenty.data.models.handlers.getKategoriaAll

class Kategorie: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<KategoriaAdapter.KategoriaViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_kategorie,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recylerViewKategorie = view.findViewById<RecyclerView>(R.id.recycler_viewKategorie)
        recylerViewKategorie.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = KategoriaAdapter(getKategoriaAll())
        }
    }

}