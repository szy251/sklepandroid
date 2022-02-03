package com.example.fargmenty.data.services.models
import com.google.gson.annotations.SerializedName

data class Customer(@SerializedName("Imie")var Imie: String? = null,
                    @SerializedName("Nazwisko")var Nazwisko: String? = null,
                    @SerializedName("Email")var Email : String? = null,
                    @SerializedName("Nazwa")var Nazwa: String? = null,
                    @SerializedName("Haslo")var Haslo : String? = null)