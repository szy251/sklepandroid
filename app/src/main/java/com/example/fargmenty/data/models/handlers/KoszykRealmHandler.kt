package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.KoszykRealm
import com.example.fargmenty.data.services.handlers.getKoszyk
import com.example.fargmenty.data.services.models.Koszyk
import io.realm.Realm
import io.realm.RealmResults

fun uaktualnijKoszyk(koszyk:Koszyk){
    val realm = Realm.getDefaultInstance()
    var koszykRealm = KoszykRealm()
    koszykRealm.Email = koszyk.Email
    koszykRealm.Ilosc = koszyk.Ilosc
    koszykRealm.Kod = koszyk.Kod
    realm.beginTransaction()
    realm.insertOrUpdate(koszykRealm)
    realm.commitTransaction()
}

fun usunKoszyk(){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(KoszykRealm::class.java)
    realm.commitTransaction()
}
fun usun_z_Koszyk(Kod : String){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    val row = realm.where(KoszykRealm::class.java).equalTo("Kod",Kod).findAll()
    row.deleteAllFromRealm()
    realm.commitTransaction()
}

fun isInKoszyk(Kod: String): Boolean {
    val realm = Realm.getDefaultInstance()
    return realm.where(KoszykRealm::class.java).equalTo("Kod",Kod).findFirst()?.isValid == true
}
fun getAllKoszyk(): RealmResults<KoszykRealm>? {
    val realm = Realm.getDefaultInstance()
    return realm.where(KoszykRealm::class.java).findAll()
}
fun isKoszyk(): Boolean {
    val realm = Realm.getDefaultInstance()
    return realm.where(KoszykRealm::class.java).findFirst()?.isValid == true
}

fun dostKoszyk(): Boolean {
    val realm = Realm.getDefaultInstance()
    val all = realm.where(KoszykRealm::class.java).findAll()
    all.forEach { i ->
        if(i.Kod?.let { dostProdukt(it) } == false) return false
    }
    return true
}

fun sumaCena(): Double {
    val realm = Realm.getDefaultInstance()
    val all = realm.where(KoszykRealm::class.java).findAll()
    var sum = 0.0
    all.forEach { i ->
        sum += cenaProdukt(i?.Kod!!)* i.Ilosc!!
    }
    return sum
}

fun updateRealmDBKoszyk(){
    usunKoszyk()
    getKoszyk(getEmail())
}