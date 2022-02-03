package com.example.fargmenty.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fargmenty.R

class Podsumowanie:Fragment() {
    var dane = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceBundle: Bundle?): View?{
        val przyslane = arguments?.getString("Dane").toString()
        if(przyslane.isNotEmpty())  dane = przyslane
        return inflater.inflate(R.layout.fragment_podsumowanie,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.Dane).text = dane
        val click = view.findViewById<Button>(R.id.niechSieDziejeWolaNieba)

        click.setOnClickListener(){
            val menu = Menu()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragemnt,menu)?.commit()
        }
    }
}