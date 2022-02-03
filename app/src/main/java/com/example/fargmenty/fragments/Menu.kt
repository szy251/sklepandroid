package com.example.fargmenty.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.example.fargmenty.*
import com.example.fargmenty.data.models.handlers.updateState
import com.google.android.datatransport.runtime.dagger.Provides


class Menu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_menu,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu_loggin = view.findViewById<Button>(R.id.menu_login)
        val menu_registers = view.findViewById<Button>(R.id.menu_register)
        val menu_account = view.findViewById<Button>(R.id.menu_Account)
        val menu_produkty = view.findViewById<Button>(R.id.menu_wszystkie)
        val menu_kategorie = view.findViewById<Button>(R.id.menu_kategorie)
        val menu_koszyk = view.findViewById<Button>(R.id.menu_koszyk)
        val menu_exit = view.findViewById<Button>(R.id.menu_exit)
        menu_loggin.setOnClickListener(){
            if(!logged) {
                val login = Login()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragemnt, login)
                    ?.commit()
            }
        }
        menu_registers.setOnClickListener(){
            if(!logged) {
                val register = Register()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.fragemnt, register)
                    ?.commit()
            }
        }
        menu_account.setOnClickListener(){
          if(logged){
              val kontoIntention = Intent(activity,KontoActivity::class.java)
              startActivity(kontoIntention)
              activity?.finish()
          }
        }

        menu_produkty.setOnClickListener(){
            if(zaktProd) {
                val produktyLista = ProduktyLista()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.fragemnt, produktyLista)
                    ?.commit()
            }
            else Toast.makeText(activity,"Poczekaj na aktualizacje danych",Toast.LENGTH_SHORT).show()
            }
        menu_kategorie.setOnClickListener(){
            if(zaktKateg) {
                val kategorie = Kategorie()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragemnt, kategorie)
                    ?.commit()
            }
            else Toast.makeText(activity,"Poczekaj na aktualizacje danych",Toast.LENGTH_SHORT).show()
        }
        menu_koszyk.setOnClickListener(){
            if(logged){
                if(zaktKoszyk) {
                    val koszyk = Koszyk()
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragemnt, koszyk)
                        ?.commit()
                }
                else Toast.makeText(activity,"Poczekaj na aktualizacje danych",Toast.LENGTH_SHORT).show()
            }
        }

        menu_exit.setOnClickListener(){
            activity?.finish()
        }
    }
}