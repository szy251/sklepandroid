package com.example.fargmenty.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fargmenty.R
import com.example.fargmenty.data.models.handlers.corectPassword
import com.example.fargmenty.data.models.handlers.getCustomer
import com.example.fargmenty.data.models.handlers.updatePassword
import com.example.fargmenty.data.services.handlers.putCustomer
import com.example.fargmenty.data.services.models.Customer

class ZmianaHasla: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zmianahasla, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val change = view.findViewById<Button>(R.id.changeHaslo)
        val cofnij = view.findViewById<Button>(R.id.back)


        change.setOnClickListener(){
            zmien()
        }
        cofnij.setOnClickListener(){
            val konto = Konto()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragemnt1,konto)?.commit()
        }
    }

    private fun zmien() {
        val stare = view?.findViewById<EditText>(R.id.oldPassword)
        val nowe = view?.findViewById<EditText>(R.id.newPassword)

        if(stare?.text.toString().isEmpty() or nowe?.text.toString().isEmpty())
            Toast.makeText(activity,"Wypełnij pola",Toast.LENGTH_LONG).show()
        else if(corectPassword(stare?.text.toString()))
        {
            if(popr(nowe?.text.toString())) {
                updatePassword(nowe?.text.toString())
                val customerRealm = getCustomer()
                val customer = Customer(customerRealm.Email)
                customer.Haslo = customerRealm.Haslo
                customer.Imie = customerRealm.Imie
                customer.Nazwa = customerRealm.Nazwa
                customer.Nazwisko = customerRealm.Nazwisko
                putCustomer(customer)
                val konto = Konto()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragemnt1, konto)
                    ?.commit()
            }
            else Toast.makeText(activity,"Nowe hasło nie spełnia wymagań",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(activity,"Złe dane",Toast.LENGTH_LONG).show()
        }
    }
    fun popr(Haslo: String):Boolean{
        if(Haslo.length in 8..50 && Haslo.all {it > Char(32)}) return true
        return false
    }

}