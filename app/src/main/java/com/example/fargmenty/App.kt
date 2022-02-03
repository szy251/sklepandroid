package com.example.fargmenty

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
var logged = false
var poprdan = false
val idState:Int = 1
var zaktProd = true
var zaktKoszyk = true
var zaktKateg = true
var zaktOrders = true
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("realm").build()
        Realm.setDefaultConfiguration(config)
    }
}