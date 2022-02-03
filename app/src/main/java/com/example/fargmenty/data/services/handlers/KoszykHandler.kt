package com.example.fargmenty.data.services.handlers

import android.util.Log
import com.example.fargmenty.data.models.handlers.addProduct
import com.example.fargmenty.data.models.handlers.uaktualnijKoszyk
import com.example.fargmenty.data.services.StartCustomerService
import com.example.fargmenty.data.services.StartKoszykService
import com.example.fargmenty.data.services.StartProduktService
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.Koszyk
import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.poprdan
import com.example.fargmenty.zaktKoszyk
import com.example.fargmenty.zaktProd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getKoszyk(Email : String){
    zaktKoszyk = false
    StartKoszykService().GetKoszyk(Email).enqueue(object : Callback<List<Koszyk>> {
        override fun onResponse(call: Call<List<Koszyk>>, response: Response<List<Koszyk>>){
            if (response.code() == 200) {
                if(response.body() != null) {
                    response.body()!!.forEach { k ->
                        uaktualnijKoszyk(k)
                    }
                }
                zaktKoszyk = true
                Log.d("Koszyk", response.body().toString())
            }
            else {
                Log.d("Koszyk", "Wrong data")
            }
        }

        override fun onFailure(call: Call<List<Koszyk>>, t: Throwable) {
            Log.d("Koszyk", t.message.toString())
        }
    })
}
fun putKoszyk(koszyk : Koszyk){
    StartKoszykService().PutKoszyk(koszyk).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            if (response.body()?.boolean == false) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }
            if (response.body()?.boolean == true) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }

        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Koszyk", t.message.toString())
        }})
}
fun deleteKoszyk(Email: String){
    StartKoszykService().DeleteKoszyk(Email).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            if (response.body()?.boolean == false) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }
            if (response.body()?.boolean == true) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }

        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Koszyk", t.message.toString())
        }})
}
fun deleteKoszyk(Email: String,Kod :String){
    StartKoszykService().DeleteKoszyk(Email,Kod).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            if (response.body()?.boolean == false) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }
            if (response.body()?.boolean == true) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
            }

        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Koszyk", t.message.toString())
        }})
}
fun postKoszyk(koszyk: Koszyk){
    StartKoszykService().PostKoszyk(koszyk).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
                response.body()?.message?.let { Log.d("Koszyk", it) }
        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Koszyk", t.message.toString())
        }})
}