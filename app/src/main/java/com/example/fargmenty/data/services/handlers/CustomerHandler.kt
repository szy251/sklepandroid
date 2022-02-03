package com.example.fargmenty.data.services.handlers

import android.util.Log
import com.example.fargmenty.data.models.CustomerRealm
import com.example.fargmenty.data.models.handlers.addCustomer
import com.example.fargmenty.data.services.StartCustomerService
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.fragments.Login
import com.example.fargmenty.logged
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.fargmenty.poprdan



fun GetCustomers(){
    StartCustomerService().GetCustomers().enqueue(object : Callback<List<Customer>> {
        override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
            if (response.code() == 200) {
               val realm =Realm.getDefaultInstance()
                if(response.body() != null) {
                    response.body()!!.forEach { k ->
                        addCustomer(k)
                    }
                }

                Log.d("Customers", response.body().toString())
            }
            else {
                Log.d("Customers", "Wrong data")
            }
        }

        override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
            Log.d("Customers", t.message.toString())
        }
    })
}
fun putCustomer(customer: Customer){
    StartCustomerService().PutCustomer(customer).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            if (response.body()?.boolean == false) {
                response.body()?.message?.let { Log.d("Customer", it) }
            }
            if (response.body()?.boolean == true) {
                response.body()?.message?.let { Log.d("Customer", it) }
            }

        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Customer", t.message.toString())
        }})
}
fun deleteCustomer(Email:String){
    StartCustomerService().DeleteCustomer(Email).
    enqueue(object : Callback<RespondBody> {
        override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
            if (response.body()?.boolean == false) {
                response.body()?.message?.let { Log.d("Customer", it) }
            }
            if (response.body()?.boolean == true) {
                response.body()?.message?.let { Log.d("Customer", it) }
            }

        }
        override fun onFailure(call: Call<RespondBody>, t: Throwable) {
            Log.d("Customer", t.message.toString())
        }})
}


