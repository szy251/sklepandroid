package com.example.fargmenty.fragments

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fargmenty.R
import com.example.fargmenty.data.models.handlers.*
import com.example.fargmenty.data.services.handlers.deleteKoszyk
import com.example.fargmenty.data.services.handlers.postOrder
import com.example.fargmenty.data.services.models.Order
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.poprdan

class Zakup : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zakup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nrDomu = view.findViewById<EditText>(R.id.nrDomu)
        val kodPocztowy = view.findViewById<EditText>(R.id.kodPocztowy)
        val miejscowosc = view.findViewById<EditText>(R.id.Miejscowosc)
        val finalizuj = view.findViewById<Button>(R.id.podsumowanie)

        finalizuj.setOnClickListener() {
            val sprawdz = poprDan(nrDomu.text.toString(), kodPocztowy.text.toString(), miejscowosc.text.toString())
            if(sprawdz.boolean == true){
                val opis = getImie_i_Nazwisko() + "\n" + nrDomu.text.toString() + "\n" +
                kodPocztowy.text.toString() +", " + miejscowosc.text.toString() + "\n" + "\n" +
                        "Suma: " + sumaCena().toString()
                val idOrder = Calendar.getInstance().time.toString() + " " + getEmail()
                val order = Order(idOrder, getEmail(),"Przyjęte",opis)
                addOrder(order)
                postOrder(order)
                val bundle = Bundle()
                bundle.putString("Dane",opis)
                val podsumowanie = Podsumowanie()
                podsumowanie.arguments = bundle
                usunKoszyk()
                deleteKoszyk(getEmail())
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragemnt,podsumowanie)?.commit()
            }
            else Toast.makeText(activity,sprawdz.message,Toast.LENGTH_SHORT).show()
        }
    }


            fun poprDan(nrDomu: String, kodPocztowy: String, miejscowosc: String): RespondBody {
                if (nrDomu.isEmpty() or kodPocztowy.isEmpty() or miejscowosc.isEmpty()) return RespondBody(
                    false,
                    "Puste pola"
                )
                if (nrDomu.all {
                        (it > Char(47) && it < Char(58)) or (it.isLetter()) or (it == Char(
                            32
                        )) or (it == Char(46))
                    }) {
                    if (kodPocztowy.length == 6 && kodPocztowy[2] == '-' && kodPocztowy.all {
                            (it > Char(
                                47
                            ) && it < Char(58)) or (it == '-')
                        }) {
                        if (miejscowosc.all { it.isLetter() }) {
                            return RespondBody(true, "Poprawne dane")
                        }
                        return RespondBody(false, "Zła miejscowość")
                    } else return RespondBody(false, "Zły kod pocztowy")
                } else return RespondBody(false, "Zły numer i ulica")
            }
        }
