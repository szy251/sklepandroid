package com.example.fargmenty.data.models.handlers

import com.example.fargmenty.data.models.CustomerRealm
import com.example.fargmenty.data.services.models.Customer
import io.realm.Realm

fun addCustomer(customer: Customer)
{
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    val customerRealm: CustomerRealm = realm.createObject(CustomerRealm::class.java, customer.Email)
    customerRealm.Haslo = customer.Haslo
    customerRealm.Imie = customer.Imie
    customerRealm.Nazwa = customer.Nazwa
    customerRealm.Nazwisko = customer.Nazwisko
    realm.commitTransaction()
}
fun isSomething(Email:String) : Boolean{
    val realm = Realm.getDefaultInstance()
    return realm.where(CustomerRealm::class.java).equalTo("Email",Email).findFirst()?.isValid == true
}
fun deleteCustomer(){
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.delete(CustomerRealm::class.java)
    realm.commitTransaction()
}
fun getCustomer():CustomerRealm{
    val realm = Realm.getDefaultInstance()
    return realm.where(CustomerRealm::class.java).findFirst()!!
}
fun corectPassword(Haslo : String) : Boolean{
    val realm = Realm.getDefaultInstance()
    return realm.where(CustomerRealm::class.java).equalTo("Haslo",Haslo).findFirst()?.isValid == true
}
fun updatePassword(Haslo : String){
    val realm = Realm.getDefaultInstance()
    val on= realm.where(CustomerRealm::class.java).findFirst()
    on?.Haslo=Haslo
    realm.beginTransaction()
    realm.insertOrUpdate(on!!)
    realm.commitTransaction()
}
fun getEmail():String{
    val realm = Realm.getDefaultInstance()
    return realm.where(CustomerRealm::class.java).findFirst()?.Email.toString()
}
fun getImie_i_Nazwisko(): String {
    val realm = Realm.getDefaultInstance()
    val customer = realm.where(CustomerRealm::class.java).findFirst()
    return customer?.Imie + " " + customer?.Nazwisko
}
