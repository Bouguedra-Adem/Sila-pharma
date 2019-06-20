package com.example.myapplication.viewmodel

import android.app.Application
import android.icu.text.Collator
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.model.Pharmacie
import com.example.myapplication.model.RepoPharmacie

class ViewModelPharmacie(application: Application) :AndroidViewModel(application) {
    private val RepoPharmacie :RepoPharmacie
    private val allpharm:LiveData<List<Pharmacie>>


    init {
        RepoPharmacie= RepoPharmacie(application)
        allpharm=RepoPharmacie.getAllPharmacie()
    }

    fun insertPharmacie(Pharmacie:Pharmacie){
        return RepoPharmacie.insertPharmacie(Pharmacie)
    }
    fun deltePharmacie(Pharmacie:Pharmacie){
        RepoPharmacie.deltePharmacie(Pharmacie)
    }
    fun getAllPharmacie ():LiveData<List<Pharmacie>>{
        return RepoPharmacie.getAllPharmacie()
    }
    fun getPharmacieByname(name:String) :Pharmacie?{
        return RepoPharmacie.getPharmacieByname(name)
    }
    fun delteAllPharmacie(){
        RepoPharmacie.delteAllPharmacie()

    }

}