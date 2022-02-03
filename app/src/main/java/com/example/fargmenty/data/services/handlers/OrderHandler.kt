package com.example.fargmenty.data.services.handlers

import android.util.Log
import com.example.fargmenty.data.models.handlers.addOrder
import com.example.fargmenty.data.models.handlers.addProduct
import com.example.fargmenty.data.services.StartKoszykService
import com.example.fargmenty.data.services.StartOrderService
import com.example.fargmenty.data.services.StartProduktService
import com.example.fargmenty.data.services.models.Koszyk
import com.example.fargmenty.data.services.models.Order
import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.zaktOrders
import com.example.fargmenty.zaktProd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getOrders(Email:String){
    zaktOrders = false
    StartOrderService().GetOrder(Email).enqueue(object : Callback<List<Order>> {
        override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>){
            if (response.code() == 200) {
                if(response.body() != null) {
                    response.body()!!.forEach { k ->
                        addOrder(k)
                    }
                }
                zaktOrders = true
                Log.d("Order", response.body().toString())
            }
            else {
                Log.d("Order", "Wrong data")
            }
        }

        override fun onFailure(call: Call<List<Order>>, t: Throwable) {
            Log.d("Order", t.message.toString())
        }
    })
}
fun postOrder(order: Order){
    StartOrderService().PostOrder(order).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            response.body()?.message?.let { Log.d("Order", it) }
        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Order", t.message.toString())
        }})
}