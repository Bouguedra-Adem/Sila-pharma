package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.model.Pharmacie
import com.example.myapplication.viewmodel.ViewModelPharmacie
import kotlinx.android.synthetic.main.fragment_pharmacie_list.*


class Listepharmacie : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pharmacie_list, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel=ViewModelProviders.of(activity!!).get(ViewModelPharmacie ::class.java)
        var Data=ArrayList<Pharmacie>()
        viewModel.getAllPharmacie().observe(activity !!, Observer {

            Data=it as ArrayList<Pharmacie>
        })

        listPh.layoutManager =LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        listPh.adapter=MypharmacieRecyclerViewAdapter(filtre(Data,arguments?.getString("num")!!.toInt()))
    }

    fun filtre (data:ArrayList<Pharmacie>,num:Int):ArrayList<Pharmacie>{
       val list=ArrayList<Pharmacie>()
        for(i in 0 until data.size){
            if (data[i].Num.toInt()==num){
                Log.e("wilaya",data[i].Num)
                list.add(data[i])
            }
        }
      return  list
    }
}
