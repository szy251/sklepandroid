package com.example.fargmenty.data.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.models.KategoriaRealm
import com.example.fargmenty.fragments.ProduktyLista
import com.example.fargmenty.zaktProd
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

internal class KategoriaAdapter(data : OrderedRealmCollection<KategoriaRealm?>?):RealmRecyclerViewAdapter<KategoriaRealm?,KategoriaAdapter.KategoriaViewHolder?>(data,true) {
    internal inner class KategoriaViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val kategoriaName = view.findViewById<TextView>(R.id.kategoriaNazwa)
        var data : KategoriaRealm? = null
    }

    override fun onBindViewHolder(holder: KategoriaViewHolder, position: Int) {
        val kategoria = getItem(position)
        holder.data = kategoria
        holder.kategoriaName.text=kategoria?.Nazwa

        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                if(zaktProd) {
                    val bundle = Bundle()
                    bundle.putString("kategoriaId", kategoria?.IdKategoria)
                    val produktyLista = ProduktyLista()
                    produktyLista.arguments = bundle
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragemnt, produktyLista).addToBackStack(null).commit()
                }
                else Toast.makeText(activity,"Poczekaj na aktualizacje danych", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutKategoria = layoutInflater.inflate(R.layout.kategoriaonetext,parent,false)
        return KategoriaViewHolder(layoutKategoria)
    }
}