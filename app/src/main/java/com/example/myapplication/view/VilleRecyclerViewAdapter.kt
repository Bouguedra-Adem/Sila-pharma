package com.example.myapplication.view

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.model.Pharmacie
import com.example.myapplication.model.Ville


import kotlinx.android.synthetic.main.fragment_ville.view.*

class VilleRecyclerViewAdapter(val Data: ArrayList<Ville>) : RecyclerView.Adapter<VilleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =LayoutInflater.from(parent.context).inflate(R.layout.fragment_ville,parent,false)
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
     return Data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data: Ville =Data[position]

        holder.text1.text=data.nameVille
        holder.text2.text=data.numVille.toString()
    }

    @Suppress("NAME_SHADOWING")
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
       val text1 =itemView.text1 as TextView
        val text2 =itemView.text2 as TextView

        init {
            itemView.setOnClickListener { itemView ->
                val bundle=Bundle()
                bundle.putString("num",itemView.text2.text.toString())

                //itemView.findNavController().navigate(R.id.action_listVilleFragment_to_listepharmacie,bundle)


            }
        }
    }

}
