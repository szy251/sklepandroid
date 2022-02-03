package com.example.fargmenty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.fargmenty.data.models.CustomerRealm
import com.example.fargmenty.data.models.State
import com.example.fargmenty.data.models.handlers.*
import com.example.fargmenty.data.services.models.Kategoria
import com.example.fargmenty.data.services.models.Koszyk
import com.example.fargmenty.data.services.models.Produkt
import com.example.fargmenty.fragments.*
import io.realm.Realm
import com.example.fargmenty.logged
class MainActivity : AppCompatActivity() {
    val login = Login()
    lateinit var realm : Realm
    val menu = Menu()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm =Realm.getDefaultInstance()
        updateRealmDBKategoria()
        updateRealmDBProdukt()
        /*updateState(false)
        deleteCustomer()*/
        val state = realm.where(State::class.java).equalTo("id",idState).findFirst()
        logged = state?.logged == true
        if(logged){
            updateRealmDBKoszyk()
            setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragemnt, menu)
                commit()
            }
        }
        else {
            setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragemnt, login)
                commit()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        val ak = supportFragmentManager
        val count =ak.backStackEntryCount
        if(count != null)
        {
            for(i in 0 until count) ak.popBackStack()
        }
         if(id == R.id.Menu )supportFragmentManager.beginTransaction().apply{
             replace(R.id.fragemnt,menu)
             commit()
         }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val ak = supportFragmentManager
        val count =ak.backStackEntryCount
        if(count >= 1)
            ak.popBackStackImmediate()
    }

    /*fun add(){
        deleteProducts()
        deleteKategorias()
        usunKoszyk()
        var cena = 10.0
        addProduct(Produkt("Skakanka",cena,"2",true))
        cena = 7.53
        addProduct(Produkt("Hulajnoga",cena,"1",true))
        cena = 11.77
        addProduct(Produkt("Sztanga",cena,"2",false))
        cena = 21.33
        addProduct(Produkt("Paraletki",cena,"2",true))
        cena = 3.28
        addProduct(Produkt("Hantelek",cena,"2",true))
        cena = 2.46
        addProduct(Produkt("Piłka",cena,"1",false))
        cena = 900.94
        addProduct(Produkt("Rower",cena,"1",true))
        addKategoria(Kategoria("1","Spport ogólne"))
        addKategoria(Kategoria("2","Fitness"))
*//*        uaktualnijKoszyk(Koszyk(1,"Piłka","szy251@gmail.com"))
        uaktualnijKoszyk(Koszyk(1,"Hantelek","szy251@gmail.com"))
        uaktualnijKoszyk(Koszyk(1,"Skakanka","szy251@gmail.com"))*//*
    }*/
}