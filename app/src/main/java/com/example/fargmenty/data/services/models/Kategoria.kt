package com.example.fargmenty.data.services.models

import com.google.gson.annotations.SerializedName

data class Kategoria(@SerializedName("IdKategoria")var IdKategoria : String? = null,
                     @SerializedName("Nazwa")var Nazwa: String? = null)
