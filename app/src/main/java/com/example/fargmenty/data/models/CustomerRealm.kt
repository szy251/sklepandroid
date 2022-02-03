package com.example.fargmenty.data.models
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.serialization.Serializable
open class CustomerRealm():RealmObject(){
    @PrimaryKey
    var Email : String? = null
    var Imie : String? = null
    var Nazwisko : String? = null
    var Nazwa : String? = null
    var Haslo : String? = null
}