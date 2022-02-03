package com.example.fargmenty.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class OrderRealm: RealmObject() {
    @PrimaryKey
    var OrderId: String? = null
    var Email :String? = null
    var Status: String? = null
    var Opis: String? = null
}