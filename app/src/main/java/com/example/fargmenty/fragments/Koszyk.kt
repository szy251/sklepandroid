package com.example.fargmenty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.adapters.KoszykAdapter
import com.example.fargmenty.data.adapters.ProduktAdapter
import com.example.fargmenty.data.models.handlers.dostKoszyk
import com.example.fargmenty.data.models.handlers.getAllKoszyk
import com.example.fargmenty.data.models.handlers.isKoszyk

class Koszyk: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<KoszykAdapter.viewHolderKoszyk>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_koszyk,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler_viewKoszyk = view.findViewById<RecyclerView>(R.id.recycler_viewKoszyk)
        recycler_viewKoszyk.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = KoszykAdapter(getAllKoszyk())
        }

        val zloz_zamowienie = view.findViewById<Button>(R.id.zamowienie)

        zloz_zamowienie.setOnClickListener(){
            if(isKoszyk() && dostKoszyk())
            {
                val zakup = Zakup()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragemnt,zakup)?.commit()
            }
            else Toast.makeText(activity,"Brak prodktów lub produkt nie jest dostępny",Toast.LENGTH_SHORT).show()
        }
    }
}