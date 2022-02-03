package com.example.fargmenty.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.models.KoszykRealm
import com.example.fargmenty.data.models.handlers.getEmail
import com.example.fargmenty.data.models.handlers.uaktualnijKoszyk
import com.example.fargmenty.data.models.handlers.usun_z_Koszyk
import com.example.fargmenty.data.services.handlers.deleteKoszyk
import com.example.fargmenty.data.services.handlers.putKoszyk
import com.example.fargmenty.data.services.models.Koszyk
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

internal class KoszykAdapter(data : OrderedRealmCollection<KoszykRealm?>?):RealmRecyclerViewAdapter<KoszykRealm?,KoszykAdapter.viewHolderKoszyk?>(data,true) {
    internal inner class viewHolderKoszyk(view: View):RecyclerView.ViewHolder(view){
        val usun = view.findViewById<Button>(R.id.usun_z_koszyka)
        val plus = view.findViewById<Button>(R.id.plus)
        val minus = view.findViewById<Button>(R.id.minus)
        val ilosc = view.findViewById<TextView>(R.id.ilosc_koszyk)
        val nazwa = view.findViewById<TextView>(R.id.nazwakoszyk)
        var data : KoszykRealm? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderKoszyk {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutKoszykPojedynczy = layoutInflater.inflate(R.layout.koszykprzedmiot,parent,false)
        return viewHolderKoszyk(layoutKoszykPojedynczy)
    }

    override fun onBindViewHolder(holder: viewHolderKoszyk, position: Int) {
        val koszyk = getItem(position)
        holder.data = koszyk
        holder.ilosc.text = koszyk?.Ilosc.toString()
        holder.nazwa.text = koszyk?.Kod

        holder.plus.setOnClickListener(){
            val koszyczek = Koszyk(koszyk?.Ilosc?.plus(1),koszyk?.Kod,koszyk?.Email)
            uaktualnijKoszyk(koszyczek)
            putKoszyk(koszyczek)
        }
        holder.minus.setOnClickListener(){
            if(koszyk?.Ilosc!! > 1) {
                val koszyczek = Koszyk(koszyk.Ilosc?.minus(1), koszyk.Kod, koszyk.Email)
                uaktualnijKoszyk(koszyczek)
                putKoszyk(koszyczek)
            }
            else Toast.makeText(holder.itemView.context, "Nie może byś mniej niż 1 przedmiot", Toast.LENGTH_SHORT).show()
        }
        holder.usun.setOnClickListener(){
            koszyk?.Kod?.let { it1 -> deleteKoszyk(getEmail(), it1) }
            koszyk?.Kod?.let { it1 -> usun_z_Koszyk(it1) }
        }
    }
}