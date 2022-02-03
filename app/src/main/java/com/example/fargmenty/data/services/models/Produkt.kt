package com.example.fargmenty.data.services.models

import com.google.gson.annotations.SerializedName

data class Produkt(@SerializedName("Nazwa")var Nazwa : String? = null,
                   @SerializedName("Cena")var Cena : Double? = null,
                   @SerializedName("IdKategoria")var IdKategoria : String? = null,
                   @SerializedName("Dostepnosc")var Dostepnosc: Boolean? = null)
