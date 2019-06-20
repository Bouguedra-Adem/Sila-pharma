package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.model.RepoUser
import com.example.myapplication.model.User

class ViewModelUser (application : Application):AndroidViewModel(application) {
   private val repoUser:RepoUser?

    init {
        repoUser= RepoUser(application)
    }
   fun inserUser(user:User){
       repoUser!!.insertUser(user)

   }
    fun deleteUser(user:User){
        repoUser?.delteUser(user)
    }
    fun getUserByTelf(telf:String):User?{
        return  repoUser!!.GetUserbyTelf(telf)
    }
    fun getAllPharmacie ():LiveData<List<User>>{
        return repoUser!!.getAllUser()
    }
}