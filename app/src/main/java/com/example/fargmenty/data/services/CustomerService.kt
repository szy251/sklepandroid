package com.example.fargmenty.data.services
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.RespondBody
import retrofit2.Call
import retrofit2.http.*

interface CustomerService {
    @Headers("Content-Type: application/json")
    @POST("customer")
    fun PostCustomer(@Body customer: Customer): Call<RespondBody>

    @GET("customer")
    fun GetCustomers(): Call<List<Customer>>

    @GET("customer/{Email}/{Haslo}")
    fun GetCustomer(@Path("Email")Email:String,@Path("Haslo")Haslo:String) :Call<List<Customer>>

    @PUT("customer")
    fun PutCustomer(@Body customer: Customer): Call<RespondBody>

    @DELETE("customer/{Email}")
    fun DeleteCustomer(@Path("Email")Email:String) : Call<RespondBody>
}