package com.example.myapplication.Map.Common.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit:Retrofit?=null

    fun getCilent (baseUrll:String):Retrofit{
        if(retrofit ==null){
            retrofit =Retrofit.Builder()
                .baseUrl(baseUrll)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}