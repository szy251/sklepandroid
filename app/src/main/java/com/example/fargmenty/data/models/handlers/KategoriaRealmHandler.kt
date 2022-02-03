package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.KategoriaRealm
import com.example.fargmenty.data.models.ProduktRealm
import com.example.fargmenty.data.services.handlers.getKategoria
import com.example.fargmenty.data.services.models.Kategoria
import io.realm.Realm
import io.realm.RealmResults

fun addKategoria(kategoria:Kategoria){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    val kategoriaRealm : KategoriaRealm = realm.createObject(KategoriaRealm::class.java,kategoria.IdKategoria)
    kategoriaRealm.Nazwa = kategoria.Nazwa
    realm.commitTransaction()
}

fun deleteKategorias(){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(KategoriaRealm::class.java)
    realm.commitTransaction()
}
fun isKategoriaValid(IdKategoria:String): Boolean{
    val realm = Realm.getDefaultInstance()
    return realm.where(KategoriaRealm::class.java).equalTo("KategoriaId" ,IdKategoria).findFirst()!!.isValid
}

fun getKategoriaAll(): RealmResults<KategoriaRealm>? {
    val realm = Realm.getDefaultInstance()
    return realm.where(KategoriaRealm::class.java).findAll()
}

fun updateRealmDBKategoria(){
    deleteKategorias()
    getKategoria()
}