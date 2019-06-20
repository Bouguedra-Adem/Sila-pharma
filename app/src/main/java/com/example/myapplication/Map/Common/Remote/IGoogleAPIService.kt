package com.example.myapplication.Map.Common.Remote

import com.example.myapplication.Map.Common.modelMap.Myplaces
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IGoogleAPIService {
    @GET
    fun getNearByPlaces(@Url url:String):Call<Myplaces>
}