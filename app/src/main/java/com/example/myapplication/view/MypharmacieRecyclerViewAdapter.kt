package com.example.myapplication.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.model.Pharmacie
import kotlinx.android.synthetic.main.fragment_pharmacie.view.*
import kotlinx.android.synthetic.main.fragment_ville.view.*
import android.content.Intent

import android.net.Uri
import androidx.core.content.ContextCompat.startActivity


class MypharmacieRecyclerViewAdapter( val data:List<Pharmacie>) : RecyclerView.Adapter<MypharmacieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.myapplication.R.layout.fragment_pharmacie, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return  data.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data:Pharmacie=data[position]
        holder.text1.text=data.namepharmacie
        holder.text2.text=data.adr
        holder.text3.text=data.Num
        holder.btn2.setOnClickListener{
            val bundle= Bundle()

            bundle.putDouble("lat",data.lat)
            bundle.putDouble("lng",data.lng)
            bundle.putString("namePlace",data.namepharmacie)
           it.findNavController().navigate(com.example.myapplication.R.id.action_listepharmacie_to_mapLien2,bundle)
        }
        holder.btn1.setOnClickListener{

            val uris = Uri.parse(data.lienFB)
            val intents = Intent(Intent.ACTION_VIEW, uris)
            startActivity(it.context,intents!!,null)
        }

    }


     class ViewHolder( val mView: View) : RecyclerView.ViewHolder(mView) {
         val text1=mView.textname as TextView
         val text2=mView.horaire as TextView
         val text3=mView.telp as TextView
         val btn1=mView.fb as ImageButton
         var btn2=mView.map as ImageButton

         }


    }

