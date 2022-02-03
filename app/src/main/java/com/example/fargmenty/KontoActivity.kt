package com.example.fargmenty

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fargmenty.data.models.handlers.updateRealmDBOrder
import com.example.fargmenty.fragments.Konto

class KontoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        updateRealmDBOrder()
        super.onCreate(savedInstanceState)
        val konto = Konto()
        setContentView(R.layout.activity_konto)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragemnt1,konto)
            commit()
        }
    }
    override fun onBackPressed() {
        val ak = supportFragmentManager
        val count =ak.backStackEntryCount
        if(count >= 1)
            ak.popBackStackImmediate()
    }
}