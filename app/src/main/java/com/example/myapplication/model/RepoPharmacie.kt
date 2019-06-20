package com.example.myapplication.model

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData


class RepoPharmacie (application: Application) {
    private val pharmacieDa:PharmacieDao?
     private val allPharmacie:LiveData<List<Pharmacie>>
    private val InitPharmacie:List<Pharmacie>
    init {
        val db=pharmacieDatabase.getInstance(application)



        pharmacieDa=db?.PharmacieDao()

        val data=DataPharmacie()
        InitPharmacie=data.genratePharmacieWithVille()

         for (i in 0 until InitPharmacie.size){
           insertPharmacie(InitPharmacie[i])


         }
        allPharmacie=pharmacieDa!!.getAllPharmacie()
    }

    fun insertPharmacie(Pharmacie:Pharmacie){
        inserAsynckTasck(pharmacieDa!!).execute(Pharmacie)
    }
    fun delteAllPharmacie(){
        DeleteAllAsynckTasck(pharmacieDa!!).execute()
    }
    fun deltePharmacie(Pharmacie:Pharmacie){
        DeleteAsynckTasck(pharmacieDa!!).execute(Pharmacie)

    }
    fun getPharmacieByname(name:String) : Pharmacie? {
        val AllPharList=allPharmacie.value?.toList()
        AllPharList?.iterator()?.forEach {
            if (it.namepharmacie==name){
                return it
            }
        }
        return null
    }
    fun getAllPharmacie():LiveData<List<Pharmacie>>{
        return  allPharmacie
    }
    private  class inserAsynckTasck (private val dao: PharmacieDao):AsyncTask<Pharmacie,Void,Void> (){
        override fun doInBackground(vararg params: Pharmacie?): Void? {
            dao.insertPharmacie(params[0]!!)
            return null
        }

    }
    private  class DeleteAsynckTasck (private val dao: PharmacieDao):AsyncTask<Pharmacie,Void,Void> (){
        override fun doInBackground(vararg params: Pharmacie?): Void? {
            dao.deltePharmacie(params[0] !!)
            return null

        }

    }
    private  class DeleteAllAsynckTasck (private val dao: PharmacieDao):AsyncTask<Void,Void,Void> (){
        override fun doInBackground(vararg params: Void): Void? {
            dao.delteAllPharmacie()
            return null

        }

    }

}