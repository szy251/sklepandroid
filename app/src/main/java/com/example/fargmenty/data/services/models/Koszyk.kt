package com.example.fargmenty.data.services.models

import com.google.gson.annotations.SerializedName

class Koszyk(@SerializedName("Ilosc")var Ilosc : Int? = null,
             @SerializedName("Kod")var Kod : String? = null,
             @SerializedName("Email")var Email : String? = null) {
}