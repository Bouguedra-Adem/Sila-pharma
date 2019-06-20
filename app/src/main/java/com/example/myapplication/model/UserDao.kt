package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User)
    @Delete
    fun delteUser(User:User)

    @Query("Select * from User where numTlf= :tel")
    fun getUserByTelp(tel:String) :LiveData<User>

    @Query("Select * from User ORDER BY NSS ASC")
    fun getAllUser():LiveData<List<User>>

}