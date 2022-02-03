package com.example.fargmenty.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class State: RealmObject (){
    @PrimaryKey
    var id: Int? = null
    var logged : Boolean? = false
}