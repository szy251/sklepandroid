package com.example.fargmenty.data.adapters

import android.app.Activity
import android.app.AppComponentFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.models.ProduktRealm
import com.example.fargmenty.fragments.ProduktInfo
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

internal class ProduktAdapter(data: OrderedRealmCollection<ProduktRealm?>?):RealmRecyclerViewAdapter<ProduktRealm?,ProduktAdapter.viewHolderProdukt?>(data,true) {


    internal inner class viewHolderProdukt(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazwaView = itemView.findViewById<TextView>(R.id.ProduktNazwa)
        val szczegolyView = itemView.findViewById<Button>(R.id.ProduktSzczegoly)
        val dostepnoscView = itemView.findViewById<TextView>(R.id.ProduktDostepnosc)
        val dosteonoscInfoView = itemView.findViewById<TextView>(R.id.DostepnoscIndfo)
        var data: ProduktRealm?=null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderProdukt {
        val layoutInflater =LayoutInflater.from(parent.context)
        val layoutProdukt = layoutInflater.inflate(R.layout.produkt_basicinfo,parent,false)
        return viewHolderProdukt(layoutProdukt)
    }

    override fun onBindViewHolder(holder: viewHolderProdukt, position: Int) {
        val produkt = getItem(position)
        holder.data= produkt
        holder.nazwaView.text = produkt?.Nazwa
        if(produkt?.Dostepnosc==true) holder.dosteonoscInfoView.text = "Dostępny"
        else holder.dosteonoscInfoView.text = "Niedostepny"

        holder.szczegolyView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val bundle = Bundle()
                bundle.putString("nazwa",produkt?.Nazwa)
                bundle.putDouble("cena", produkt?.Cena!!)
                bundle.putBoolean("dostepnosc",produkt.Dostepnosc!!)
                val produktInfo = ProduktInfo()
                produktInfo.arguments = bundle
                val activity = v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().
                replace(R.id.fragemnt,produktInfo).addToBackStack(null).commit()
            }
        })
    }
}