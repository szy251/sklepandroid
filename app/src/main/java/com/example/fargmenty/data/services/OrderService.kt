package com.example.fargmenty.data.services

import com.example.fargmenty.data.services.models.Order
import com.example.fargmenty.data.services.models.RespondBody
import retrofit2.Call
import retrofit2.http.*

interface OrderService {
    @POST("order")
    fun PostOrder(@Body order: Order): Call<RespondBody>

    @GET("order/{Email}")
    fun GetOrders(@Path("Email")Email:String): Call<List<Order>>

    @GET("order/{Id}")
    fun GetOrder(@Path("Id")Id:String): Call<List<Order>>

    @PUT("order")
    fun PutOrder(@Body order: Order): Call<RespondBody>

    @DELETE("order/{Id}")
    fun DeleteOrder(@Path("Id")Id:String) : Call<RespondBody>
}