package com.example.fargmenty.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.example.fargmenty.R
import com.example.fargmenty.data.models.handlers.addCustomer
import com.example.fargmenty.data.models.handlers.isSomething
import com.example.fargmenty.data.models.handlers.updateState
import com.example.fargmenty.data.services.StartCustomerService
import com.example.fargmenty.data.services.handlers.getKoszyk
import com.example.fargmenty.data.services.models.Customer
import com.example.fargmenty.data.services.models.RespondBody
import com.example.fargmenty.logged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val zaloguj = view.findViewById<Button>(R.id.zaloguj)
        zaloguj.setOnClickListener()
        {
            logowanie()
        }
    }
    private fun logowanie()
    {
        val Email = view?.findViewById<EditText>(R.id.editTextTextEmailAddress)
        val Haslo = view?.findViewById<EditText>(R.id.editTextTextPassword)
        GetCustomer(Email?.text.toString(),Haslo?.text.toString())
    }
    fun logowanie(Email : String) {
        if (isSomething(Email)) {
            getKoszyk(Email)
            logged = true
            updateState(logged)
            val produktyLista = ProduktyLista()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.fragemnt, produktyLista)
                ?.commit()
            view?.findViewById<TextView>(R.id.textView14)?.setText("")
        } else {
            view?.findViewById<TextView>(R.id.textView14)?.setText("Złe dane")
        }
    }
    fun GetCustomer(Email:String,Haslo : String){
        StartCustomerService().GetCustomer(Email,Haslo).enqueue(object : Callback<List<Customer>> {
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                if (response.code() == 200) {
                    if(response.body() != null) {
                        response.body()!!.forEach { k ->
                            addCustomer(k)
                        }
                        logowanie(Email)
                    }

                    Log.d("Customer", response.body().toString())
                }
                else {
                    Log.d("Customer", "Wrong data")
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Log.d("Customer", t.message.toString())
            }
        })
    }
}