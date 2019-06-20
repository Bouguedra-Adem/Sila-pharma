package com.example.myapplication.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class User(@PrimaryKey @NonNull var NSS:Int,
           @NonNull var  fistname:String,
           @NonNull var  lastname:String,
           @NonNull var adr :String,
           @NonNull var numTlf:String,
           @NonNull var password:String

           ){
}