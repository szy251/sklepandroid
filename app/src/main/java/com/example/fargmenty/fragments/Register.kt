package com.example.fargmenty.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fargmenty.R
import com.example.fargmenty.MainActivity
import com.example.fargmenty.data.models.handlers.addCustomer
import com.example.fargmenty.data.models.handlers.updateState
import com.example.fargmenty.data.services.StartCustomerService
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.logged
import com.example.fargmenty.poprdan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_register,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val zarejestruj = view.findViewById<Button>(R.id.zarejestruj)
        zarejestruj.setOnClickListener(){
            dodaj()
        }
    }
    private fun dodaj()
    {
        var Imie = view?.findViewById<EditText>(R.id.dajImie)
        var Nazwisko = view?.findViewById<EditText>(R.id.dajNazwisko)
        var Email = view?.findViewById<EditText>(R.id.dajEmail)
        var Nazwa = view?.findViewById<EditText>(R.id.dajNazwa)
        var Haslo= view?.findViewById<EditText>(R.id.dajHaslo)

        val customer = Customer(Imie?.text.toString(),Nazwisko?.text.toString(),Email?.text.toString(),Nazwa?.text.toString(),Haslo?.text.toString())
        val ono = Poprawnosc(customer)

        if(ono.boolean == true) {
            PostCustomer(customer)

        }
        else{
            view?.findViewById<TextView>(R.id.textView10)?.setText(ono.message)
        }
    }

    fun Poprawnosc(customer: Customer):RespondBody{
        if(customer.Email.isNullOrEmpty()||customer.Haslo.isNullOrEmpty()||customer.Nazwa.isNullOrEmpty()||customer.Imie.isNullOrEmpty()||customer.Nazwisko.isNullOrEmpty()) return RespondBody(false,"Uzupełnij dane")
        if(customer.Email?.contains("@") ==  true && customer.Email?.length!! < 51 && customer.Email?.all {it > Char(32)}!!)
        {
            if(customer.Haslo?.length!! in 8..50 && customer.Haslo?.all {it > Char(32)}!!)
            {
                if(customer.Imie?.length!! <51 && customer.Imie?.all { it.isLetter() }!!)
                {
                    if(customer.Nazwisko?.length!! <51 && customer.Nazwisko?.all { it.isLetter() }!!) {

                        if(customer.Nazwisko?.length!! <51 && customer.Nazwa?.all {it > Char(32)}!!) return RespondBody(true, "")

                        else return RespondBody(false,"Niepoprawna nazwa")
                    }
                    else return RespondBody(false,"Niepoprawne nazwisko")
                }
                else return RespondBody(false,"Niepoprawne imie")
            }
            else return RespondBody(false,"Niepoprawne haslo")
        }
        else return RespondBody(false,"Niepoprawny email")
    }

    fun PostCustomer(customer: Customer){
        StartCustomerService().PostCustomer(customer).
        enqueue(object : Callback<RespondBody> {
            override fun onResponse(call: Call<RespondBody>, response: Response<RespondBody>) {
                if (response.body()?.boolean == false) {
                    response.body()?.message?.let { Log.d("Customer", it) }
                }
                if (response.body()?.boolean == true) {
                    poprdan = true
                }
                dalej(customer)
            }
            override fun onFailure(call: Call<RespondBody>, t: Throwable) {
                Log.d("Customer", t.message.toString())
            }})
    }
    fun dalej(customer: Customer){
        if (!poprdan) {
            view?.findViewById<TextView>(R.id.textView10)?.setText("Email zajęty")
        } else {
            view?.findViewById<TextView>(R.id.textView10)?.setText("")
            logged = true
            poprdan=false
            updateState(true)
            addCustomer(customer)
            val produktyLista = ProduktyLista()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragemnt, produktyLista)
                ?.commit()
        }
    }

}


