package com.example.fargmenty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fargmenty.R
import com.example.fargmenty.data.models.handlers.getEmail
import com.example.fargmenty.data.models.handlers.isInKoszyk
import com.example.fargmenty.data.models.handlers.uaktualnijKoszyk
import com.example.fargmenty.data.services.handlers.postKoszyk
import com.example.fargmenty.data.services.models.Koszyk
import com.example.fargmenty.logged

class ProduktInfo:Fragment() {
    var prouktrealm :String = ""
    var produktdosteonosc : Boolean = false
    var cena : Double = 0.0
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        val produktRealm = arguments?.getString("nazwa")
        if (produktRealm != null) {
            prouktrealm= produktRealm
        }
        val produktRealm2 = arguments?.getBoolean("dostepnosc")
        if(produktRealm2 != null){
            produktdosteonosc = produktRealm2
        }
        val cenaRealm = arguments?.getDouble("cena")
        if(cenaRealm != null){
            cena = cenaRealm
        }
        return inflater.inflate(R.layout.fragment_produktmoreinfo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.NazwaProduktMore).text = prouktrealm
        view.findViewById<TextView>(R.id.cenainfo).text = "$cena zł"
        if(produktdosteonosc)view.findViewById<TextView>(R.id.dostepnosc_szczeg).text = "Dostępny"
        else view.findViewById<TextView>(R.id.dostepnosc_szczeg).text = "Niedostępny"
        super.onViewCreated(view, savedInstanceState)
        val dodaj = view.findViewById<Button>(R.id.toKoszyk)

        dodaj.setOnClickListener(){
            if(logged) {
                if (isInKoszyk(prouktrealm)) Toast.makeText(
                    activity,
                    "Już istnieje w koszyku",
                    Toast.LENGTH_SHORT
                ).show()
                else {
                    if (produktdosteonosc) {
                        uaktualnijKoszyk(Koszyk(1, prouktrealm, getEmail()))
                        Toast.makeText(
                            activity,
                            "Produkt dodany",
                            Toast.LENGTH_SHORT
                        ).show()
                        postKoszyk(Koszyk(1, prouktrealm, getEmail()))
                    }
                    else Toast.makeText(
                        activity,
                        "Produkt niedostępny",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else Toast.makeText(
                activity,
                "Musisz być zalogowany",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}