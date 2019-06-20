package com.example.myapplication.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity (tableName = "pharmacie_table")
data class Pharmacie(@PrimaryKey @NonNull var namepharmacie: String,
                     @NonNull  var  Ho:String,
                     @NonNull  var  Hf:String,
                     @NonNull val adr :String,
                     @NonNull val wilaya :String,
                     val Num :String,
                     @NonNull  val caisse :String,
                     @NonNull  val lienFB :String,
                     @NonNull val lat:Double,
                     @NonNull val lng :Double


) {
}