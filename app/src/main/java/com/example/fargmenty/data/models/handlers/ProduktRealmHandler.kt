package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.ProduktRealm
import com.example.fargmenty.data.services.handlers.getProdukts
import com.example.fargmenty.data.services.models.Produkt
import io.realm.Realm
import io.realm.RealmResults

fun getProductAll(): RealmResults<ProduktRealm>? {
    val realm = Realm.getDefaultInstance()
    return realm.where(ProduktRealm::class.java).findAll()
}
fun addProduct(produkt: Produkt)
{
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    val produktRealm:ProduktRealm = realm.createObject(ProduktRealm::class.java, produkt.Nazwa)
    produktRealm.Cena = produkt.Cena
    produktRealm.Dostepnosc = produkt.Dostepnosc
    produktRealm.IdKategoria = produkt.IdKategoria
    realm.commitTransaction()
}
fun getByKategoria(kategoria:String):RealmResults<ProduktRealm>?{
    val realm = Realm.getDefaultInstance()
    return realm.where(ProduktRealm::class.java).equalTo("IdKategoria",kategoria).findAll()
}

fun deleteProducts(){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(ProduktRealm::class.java)
    realm.commitTransaction()
}

fun dostProdukt(Nazwa : String): Boolean? {
    val realm = Realm.getDefaultInstance()
    val produkt = realm.where(ProduktRealm::class.java).equalTo("Nazwa",Nazwa).findFirst()
    return produkt?.Dostepnosc
}

fun cenaProdukt(Nazwa: String): Double {
    val realm = Realm.getDefaultInstance()
    val produkt = realm.where(ProduktRealm::class.java).equalTo("Nazwa",Nazwa).findFirst()
    return produkt?.Cena!!
}

fun updateRealmDBProdukt()
{
    deleteProducts()
    getProdukts()
}