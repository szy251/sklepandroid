package com.example.fargmenty.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fargmenty.*
import com.example.fargmenty.data.models.handlers.deleteCustomer
import com.example.fargmenty.data.models.handlers.getCustomer
import com.example.fargmenty.data.models.handlers.getEmail
import com.example.fargmenty.data.models.handlers.updateState
import com.example.fargmenty.data.services.handlers.deleteCustomer
import com.example.fargmenty.data.services.models.Koszyk

class Konto: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_konto,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customer = getCustomer()
        view.findViewById<TextView>(R.id.textView11).setText(customer.Imie)
        view.findViewById<TextView>(R.id.textView12).setText(customer.Nazwisko)
        view.findViewById<TextView>(R.id.textView13).setText(customer.Email)
        view.findViewById<TextView>(R.id.textView16).setText(customer.Nazwa)
        val zmienHaslo = view.findViewById<Button>(R.id.changePaswordFragment)
        val wyloguj = view.findViewById<Button>(R.id.unlogin)
        val usunKonto = view.findViewById<Button>(R.id.deleteAccount)
        val zamowienie = view.findViewById<Button>(R.id.ordersKonto)
        val menu = view.findViewById<Button>(R.id.backMenu)

        zmienHaslo.setOnClickListener(){
            val zmianaHasla = ZmianaHasla()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.fragemnt1, zmianaHasla)
                ?.commit()
        }
        wyloguj.setOnClickListener(){
            logged=false;
            updateState(false)
            deleteCustomer()
            val loginIntention = Intent(activity, MainActivity::class.java)
            startActivity(loginIntention)
            activity?.finish()
        }
        usunKonto.setOnClickListener(){
            logged=false;
            updateState(false)
            deleteCustomer(getEmail())
            deleteCustomer()
            val loginIntention = Intent(activity, MainActivity::class.java)
            startActivity(loginIntention)
            activity?.finish()
        }
        menu.setOnClickListener(){
            val loginIntention = Intent(activity, MainActivity::class.java)
            startActivity(loginIntention)
            activity?.finish()
        }

        zamowienie.setOnClickListener(){
            if(zaktOrders) {
                val zamowienia = Zamowienia()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.fragemnt1, zamowienia)
                    ?.addToBackStack(null)?.commit()
            }
            else Toast.makeText(activity,"Czekaj na aktualizację bazy danych",Toast.LENGTH_SHORT).show()
        }

    }
}