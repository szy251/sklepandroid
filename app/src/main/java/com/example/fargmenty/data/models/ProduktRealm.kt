package com.example.fargmenty.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ProduktRealm : RealmObject(){
    @PrimaryKey
    var Nazwa : String? = null
    var Cena : Double? = null
    var IdKategoria : String? = null
    var Dostepnosc: Boolean? = null
}