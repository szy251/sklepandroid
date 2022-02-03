package com.example.fargmenty.data.services

import com.example.fargmenty.data.services.models.Kategoria
import com.example.fargmenty.data.services.models.RespondBody
import retrofit2.Call
import retrofit2.http.*

interface KategoriaService {
    @POST("kategoria")
    fun PostKategoria(@Body kategoria : Kategoria): Call<RespondBody>

    @GET("kategoria")
    fun GetKategorias(): Call<List<Kategoria>>

    @GET("kategoria/{IdKategoria}")
    fun GetKategoria(@Path("IdKategoria")IdKategoria:String) : Call<List<Kategoria>>

    @PUT("kategoria")
    fun PutKategoria(@Body kategoria: Kategoria): Call<RespondBody>

    @DELETE("kategoria/{IdKategoria}")
    fun DeleteKategoria(@Path("IdKategoria")IdKategoria:String) : Call<RespondBody>
}