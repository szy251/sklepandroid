package com.example.fargmenty.data.services.models

import com.google.gson.annotations.SerializedName

data class RespondBody(@SerializedName("boolean")var boolean: Boolean?= false, @SerializedName("message") var message:String?=null)
