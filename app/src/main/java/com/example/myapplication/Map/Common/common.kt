package com.example.myapplication.Map.Common

import com.example.myapplication.Map.Common.Remote.IGoogleAPIService
import com.example.myapplication.Map.Common.Remote.RetrofitClient

object common {

    private val GOOGLE_API_URL="https://maps.googlemaps.com/"
    val googleApiService: IGoogleAPIService
    get()= RetrofitClient.getCilent(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}