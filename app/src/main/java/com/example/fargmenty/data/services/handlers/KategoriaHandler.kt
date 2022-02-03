package com.example.fargmenty.data.services.handlers

import android.util.Log
import com.example.fargmenty.data.models.handlers.addKategoria
import com.example.fargmenty.data.models.handlers.addProduct
import com.example.fargmenty.data.services.StartKategoriaService
import com.example.fargmenty.data.services.StartProduktService
import com.example.fargmenty.data.services.models.Kategoria
import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.zaktKateg
import com.example.fargmenty.zaktProd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getKategoria(){
    zaktKateg = false
    StartKategoriaService().GetKategorias().enqueue(object : Callback<List<Kategoria>> {
        override fun onResponse(call: Call<List<Kategoria>>, response: Response<List<Kategoria>>){
            if (response.code() == 200) {
                if(response.body() != null) {
                    response.body()!!.forEach { k ->
                        addKategoria(k)
                    }
                }
                zaktKateg = true
                Log.d("Kategoria", response.body().toString())
            }
            else {
                Log.d("Kategoria", "Wrong data")
            }
        }

        override fun onFailure(call: Call<List<Kategoria>>, t: Throwable) {
            Log.d("Kategoria", t.message.toString())
        }
    })
}