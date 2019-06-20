package com.example.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Pharmacie::class,User::class),version = 1,exportSchema = false)
abstract class pharmacieDatabase : RoomDatabase(){
    abstract fun PharmacieDao():PharmacieDao
    abstract fun UserDao():UserDao

    companion object{
        @Volatile
        var database :pharmacieDatabase?=null
        fun getInstance(context: Context): pharmacieDatabase? {
            if (database==null){
                synchronized(pharmacieDatabase::class.java){
                    if (database==null) {

                        database=Room.databaseBuilder(context.applicationContext,pharmacieDatabase::class.java,"Pha_Database7").build()
                    }

                }
            }


            return database
        }


    }

}
