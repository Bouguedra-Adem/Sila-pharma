package com.example.myapplication.view

import android.annotation.SuppressLint

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.Ville
import com.example.myapplication.viewmodel.ViewModelPharmacie
import com.tiper.MaterialSpinner
import kotlinx.android.synthetic.main.fragment_ville.*
import kotlinx.android.synthetic.main.fragment_ville_list.*
import jrizani.jrspinner.JRSpinner
import kotlinx.android.synthetic.main.fragment_ville_list.view.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ListVilleFragment : Fragment() {

    private var model : ViewModelPharmacie ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        return inflater.inflate(R.layout.fragment_pharma_list, container, false)

    }

    @SuppressLint("WrongConstant", "PrivateResource", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val materialSpinner :MaterialSpinner=view.findViewById(R.id.material_spinner);
        setHasOptionsMenu(true)
        val personNames = arrayOf("Rahul", "Jack", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")
            val arrayAdapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, personNames)
            materialSpinner.adapter = arrayAdapter
            materialSpinner.onItemSelectedListener = object : MaterialSpinner.OnItemSelectedListener {


                override fun onNothingSelected(parent: MaterialSpinner) {


                }

                override fun onItemSelected(parent: MaterialSpinner, view: View?, position: Int, id: Long) {
                    materialSpinner.hint="Filter par wilya de :"
                }


            }

        super.onViewCreated(view, savedInstanceState)
        val data =ArrayList<Ville>()
        model= ViewModelProviders.of(activity !!).get(ViewModelPharmacie ::class.java)
        data.add(Ville("annaba",23))
        data.add(Ville("Ghilizan",48))

        list.layoutManager =LinearLayoutManager(context,LinearLayout.VERTICAL,false)
        list.adapter=VilleRecyclerViewAdapter(data)


    }


}
