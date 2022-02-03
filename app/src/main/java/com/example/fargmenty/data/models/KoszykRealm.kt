package com.example.fargmenty.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class KoszykRealm:RealmObject() {
    var Ilosc : Int? = null
    @PrimaryKey
    var Kod : String? = null
    var Email : String? = null
}