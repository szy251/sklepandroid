package com.example.fargmenty.data.services

import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.data.services.models.RespondBody
import retrofit2.Call
import retrofit2.http.*

interface ProduktService {
    @POST("produkt")
    fun PostProdukt(@Body produkt: Produkt): Call<RespondBody>

    @GET("produkt")
    fun GetProdukt(): Call<List<Produkt>>

    @GET("produkt/{Nazwa}")
    fun GetProdukt(@Path("Nazwa") Nazwa:String): Call<List<Produkt>>

    @PUT("produkt")
    fun PutProdukt(@Body produkt: Produkt): Call<RespondBody>

    @DELETE("produkt/{Nazwa}")
    fun DeleteProdukt(@Path("Nazwa") Nazwa:String): Call<RespondBody>
}