package com.example.myapplication.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.myapplication.model.User
import com.example.myapplication.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_login.*

import android.telephony.SmsManager
import com.example.myapplication.R


class Login : Fragment() {
    var mySMS = SmsManager.getDefault()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.myapplication.R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewModel=ViewModelProviders.of(activity!!).get(ViewModelUser::class.java)
        btnLogin.setOnClickListener{
            val nom=txtNom.text
            val prenom=txtPrenom.text
            val nss:Int=txtNss.text.toString().toInt()
            val numtlfp=txtTelf.text
            val adr=txtadr.text

            if (!nom.isEmpty()&&!prenom.isEmpty()&&!txtNss.text.toString().isEmpty()&&!numtlfp.isEmpty()&&!adr.isEmpty()){
                viewModel.inserUser(User(nss ,prenom.toString(),nom.toString(),adr.toString(),numtlfp.toString(),numtlfp.toString()+nss.toString()))
                viewModel.getAllPharmacie().observe(this, Observer { User ->
                    User?.let {
                        Log.e("dddd", it.toString())
                    }
                })
                Log.e("User", viewModel.getUserByTelf(numtlfp.toString()).toString())
                val msg = "Envoi de mon premier message"



            }


        }
        lnkauthentification.setOnClickListener {
            it.findNavController().navigate(R.id.auth)
        }

    }



}
