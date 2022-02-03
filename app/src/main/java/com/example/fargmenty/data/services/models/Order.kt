package com.example.fargmenty.data.services.models

import com.google.gson.annotations.SerializedName

data class Order(@SerializedName("OrderId")var OrderId: String? = null,
                 @SerializedName("Email")var Email :String? = null,
                 @SerializedName("Status")var Status: String? = null,
                 @SerializedName("Opis")var Opis: String? = null)
