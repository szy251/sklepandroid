package com.example.fargmenty.data.services.handlers

import android.util.Log
import com.example.fargmenty.data.models.CustomerRealm
import com.example.fargmenty.data.models.handlers.addProduct
import com.example.fargmenty.data.services.StartProduktService
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.zaktProd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getProdukts(){
    zaktProd = false
    StartProduktService().GetProdukt().enqueue(object : Callback<List<Produkt>>{
        override fun onResponse(call: Call<List<Produkt>>, response: Response<List<Produkt>>){
            if (response.code() == 200) {
                if(response.body() != null) {
                    response.body()!!.forEach { k ->
                        addProduct(k)
                    }
                }
                zaktProd = true
                Log.d("Produkt", response.body().toString())
            }
            else {
                Log.d("Produkt", "Wrong data")
            }
        }

        override fun onFailure(call: Call<List<Produkt>>, t: Throwable) {
            Log.d("Produkt", t.message.toString())
        }
    })
}