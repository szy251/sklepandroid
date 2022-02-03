package com.example.fargmenty.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class KategoriaRealm():RealmObject() {
    @PrimaryKey
    var IdKategoria : String? = null
    var Nazwa: String? = null
}