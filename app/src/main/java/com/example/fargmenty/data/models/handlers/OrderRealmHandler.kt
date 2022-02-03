package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.OrderRealm
import com.example.fargmenty.data.services.handlers.getOrders
import com.example.fargmenty.data.services.models.Order
import io.realm.Realm
import io.realm.RealmResults

fun addOrder(order: Order){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    val orderRealm : OrderRealm = realm.createObject(OrderRealm::class.java,order.OrderId)
    orderRealm.Email = order.Email
    orderRealm.Opis = order.Opis
    orderRealm.Status = order.Status
    realm.commitTransaction()
}

fun getAllOrders(): RealmResults<OrderRealm>? {
    val realm = Realm.getDefaultInstance()
    return realm.where(OrderRealm::class.java).findAll()
}

fun deleteOrders(){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(OrderRealm::class.java)
    realm.commitTransaction()
}

fun updateRealmDBOrder(){
    deleteOrders()
    getOrders(getEmail())
}