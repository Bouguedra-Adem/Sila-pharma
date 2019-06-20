package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PharmacieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertPharmacie(Pharmacie:Pharmacie)
      @Delete
    fun deltePharmacie(Pharmacie:Pharmacie)

    @Query("Select * from pharmacie_table  ORDER BY namepharmacie ASC")
    fun getAllPharmacie ():LiveData<List<Pharmacie>>

    @Query("Select * from pharmacie_table where namepharmacie= :name")
    fun getUserBytelf(name:String) :Pharmacie

    @Query("DELETE FROM pharmacie_table")
    fun delteAllPharmacie()



}