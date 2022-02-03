package com.example.fargmenty.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fargmenty.R
import com.example.fargmenty.data.models.OrderRealm
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import java.util.zip.Inflater

internal class OrderAdapter(data : OrderedRealmCollection<OrderRealm?>?):RealmRecyclerViewAdapter<OrderRealm?,OrderAdapter.viewHolderOrder?>(data,true) {
    internal inner class viewHolderOrder(view : View):RecyclerView.ViewHolder(view){
        val opis = view.findViewById<TextView>(R.id.daneOrder)
        val status = view.findViewById<TextView>(R.id.StatusInfo)
        var data : OrderRealm? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderOrder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutOrder = layoutInflater.inflate(R.layout.zamowienie_info,parent,false)
        return viewHolderOrder(layoutOrder)
    }

    override fun onBindViewHolder(holder: viewHolderOrder, position: Int) {
        val order = getItem(position)
        holder.data = order
        holder.opis.text= order?.Opis
        holder.status.text = order?.Status
    }
}