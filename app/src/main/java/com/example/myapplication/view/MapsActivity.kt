package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.Map.Common.Remote.IGoogleAPIService
import com.example.myapplication.Map.Common.common
import com.example.myapplication.Map.Common.modelMap.Myplaces
import com.example.myapplication.R
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var  lagtitude :Double=0.toDouble()
    private var  longtitude :Double=0.toDouble()

    private lateinit var mLastLocation:Location
    private var mMarker:Marker?=null

    //location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback:LocationCallback

    companion object {
        private const val MY_PARMESSIN_CODE: Int = 1000
    }
    lateinit var mService:IGoogleAPIService
    internal lateinit var currentPlace: Myplaces

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mService=common.googleApiService
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ChekLocationPermession()) {
                buildLocationRequest()
                buildLocationCalback()
                //naerByPlace("food")
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

            }
        }
        else{
            buildLocationRequest()
            buildLocationCalback()
            //naerByPlace("food")
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

        }
        naerByPlace("pharmacie")

    }

    private fun naerByPlace(typlace:String) {


        Log.e("hh","1")
        val url=getUrl(36.7696,7.9064,typlace)
        Log.e("hh",url)
        mService.getNearByPlaces(url)
            .enqueue(object : Callback<Myplaces> {
                override fun onResponse(call: Call<Myplaces>?, response: Response<Myplaces>?) {
                    Log.e("hh","2")
                    currentPlace = response!!.body()!!

                    Log.e("hh","2"+currentPlace.toString())
                    Log.e("hh",response!!.body()!!.results!!.size.toString())
                    //if (response.isSuccessful) {

                        for (i in 0 until response!!.body()!!.results!!.size) {
                            val markerOptions = MarkerOptions()
                            val googlePlace = response.body()!!.results!![i]
                                 Log.e("m",googlePlace.name)
                            Log.e("m",googlePlace.geometry!!.location!!.lat.toString())
                            val lat = googlePlace.geometry!!.location!!.lat
                            val lng = googlePlace.geometry!!.location!!.lng
                            Log.e("corrrdd",lat.toString()+","+lng.toString())
                            val placename = googlePlace.name
                            val latLang = LatLng(lat, lng)
                            markerOptions.position(latLang)
                            markerOptions.title(placename)
                            if (typlace.equals("pharmacie"))
                                //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                            markerOptions.snippet(i.toString())

                            mMap!!.addMarker(markerOptions)
                            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLang))
                            mMap!!.animateCamera((CameraUpdateFactory.zoomTo(15f)))
                            Log.e("hh","hhhhhhhhhhhhhhhh")
                        //}



                    }

                }

                override fun onFailure(call: Call<Myplaces>, t: Throwable) {
                      Toast.makeText(baseContext,""+t.message,Toast.LENGTH_SHORT)
                }
            })

    }

    private fun getUrl(lagtitude: Double, longtitude: Double, typlace: String): String {
       val googlePlaceUrl=StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?location=$lagtitude,$longtitude")
        googlePlaceUrl.append("&radius=1000")
        googlePlaceUrl.append("&type=$typlace")
        googlePlaceUrl.append("&keyword=pharmacie")
        googlePlaceUrl.append("&key=AIzaSyB6ly5r_Xl8ptBr4YnnXnz8pgVk49u2mNM")
        return  googlePlaceUrl.toString()
    }

    private fun buildLocationCalback() {
        locationCallback=object :LocationCallback() {
            override fun onLocationResult(p0 :LocationResult?){
                mLastLocation= p0!!.locations[p0!!.locations.size-1]
                if (mMarker !=null){
                    mMarker!!.remove()
                }
                lagtitude=mLastLocation.latitude
                longtitude=mLastLocation.longitude

                val latLng=LatLng(lagtitude,longtitude)
                val markerOption=MarkerOptions()
                    .position(latLng)
                    .title("votr Postion actuel ")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                mMarker=mMap.addMarker(markerOption)
                //move camera

                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))


            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest= LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=500
        locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f
    }

    private fun ChekLocationPermession() :Boolean{
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), MY_PARMESSIN_CODE)}
            else{
                    ActivityCompat.requestPermissions(this, arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ), MY_PARMESSIN_CODE)

                }
            return false
            }
         else{
            return true

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        mMap.clear()
        mMarker!!.remove()
        when(requestCode){
            MY_PARMESSIN_CODE->{
                if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                     if ( ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                         if (ChekLocationPermession()){
                             buildLocationRequest()
                             buildLocationCalback()
                             fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                             fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                             mMap.isMyLocationEnabled=true

                         }
                         else{
                             Toast.makeText(this,"Permession Denid",Toast.LENGTH_SHORT).show()
                         }
                     }
                }
            }
        }
    }

    override fun onStop() {
            mMap.clear()
        if (mMarker!=null)
        mMarker!!.remove()
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            super.onStop()

    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.clear()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mMap.isMyLocationEnabled = true
                mMap.clear()
            }

        }
        else
            mMap.isMyLocationEnabled=true
        mMap.clear()
        mMap.uiSettings.isZoomControlsEnabled=true

    }



}
