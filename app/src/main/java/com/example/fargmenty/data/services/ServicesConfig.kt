package com.example.fargmenty.data.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.google.gson.GsonBuilder



val url:String = "https://f146-195-150-224-57.ngrok.io"
fun StartRetrofit():Retrofit {
    return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
}
fun StartCustomerService():CustomerService{
    return StartRetrofit().create(CustomerService::class.java)
}
fun StartProduktService():ProduktService{
    return StartRetrofit().create(ProduktService::class.java)
}
fun StartKoszykService():KoszykService{
    return StartRetrofit().create(KoszykService::class.java)
}
fun StartKategoriaService():KategoriaService{
    return StartRetrofit().create(KategoriaService::class.java)
}
fun StartOrderService():OrderService{
    return StartRetrofit().create(OrderService::class.java)
}