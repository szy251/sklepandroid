package com.example.fargmenty.data.services

import com.example.fargmenty.data.services.models.Koszyk
import com.example.fargmenty.data.services.models.RespondBody
import retrofit2.Call
import retrofit2.http.*

interface KoszykService {
    @POST("koszyk")
    fun PostKoszyk(@Body koszyk : Koszyk): Call<RespondBody>

    @GET("koszyk/{Email}")
    fun GetKoszyk(@Path("Email")Email:String): Call<List<Koszyk>>

    @GET("koszyk/{Email}/{Kod}")
    fun GetKoszyk(@Path("Email")Email:String, @Path("Kod")Kod:String) : Call<List<Koszyk>>

    @PUT("koszyk")
    fun PutKoszyk(@Body koszyk: Koszyk): Call<RespondBody>

    @DELETE("koszyk/{Email}")
    fun DeleteKoszyk(@Path("Email")Email:String) : Call<RespondBody>

    @DELETE("koszyk/{Email}/{Kod}")
    fun DeleteKoszyk(@Path("Email")Email:String,@Path("Kod")Kod:String) : Call<RespondBody>
}