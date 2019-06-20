package com.example.myapplication.model

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData

class RepoUser (application:Application) {
    private val user: User? = null
    private val userDao: UserDao?
    private val alluser:LiveData<List<User>>

    init {
        val db = pharmacieDatabase.getInstance(application)
        userDao = db?.UserDao()
        alluser=userDao!!.getAllUser()
    }
    fun getAllUser():LiveData<List<User>>{
        return  alluser
    }

    fun insertUser(User: User) {
        inserAsynckTasck(userDao!!).execute(User)
    }

    fun delteUser(User: User) {
        DeleteAsynckTasck(userDao!!).execute(User)

    }

    fun GetUserbyTelf(numTelf: String): User? {
        val alluser=alluser.value?.toList()
        alluser?.iterator()?.forEach {
            Log.e("user=",it.toString())
            if (it.numTlf==numTelf){
                return it
            }
        }
     return  null
    }

    private class inserAsynckTasck(private val dao: UserDao) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User?): Void? {
            dao.insertUser(params[0]!!)
            return null
        }

    }

    private class DeleteAsynckTasck(private val dao: UserDao) : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg params: User?): Void? {
            dao.delteUser(params[0]!!)
            return null

        }
    }
}