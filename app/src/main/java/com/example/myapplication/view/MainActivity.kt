package com.example.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.example.myapplication.R

import com.example.myapplication.viewmodel.ViewModelPharmacie
import com.example.myapplication.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.http.POST

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    private lateinit var  viewmodel :ViewModelPharmacie
    private lateinit var  viewmodelUser :ViewModelUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav =Navigation.findNavController(this,R.id.fragment)
          NavigationUI.setupActionBarWithNavController(this,nav)
          //NavigationUI.setupActionBarWithNavController(this,nav,drawerLayout)
          NavigationUI.setupWithNavController(navView,nav)
         // NavigationUI.setupWithNavController(nav_bottom,nav)
        viewmodel=ViewModelProviders.of(this).get(ViewModelPharmacie ::class.java)
        viewmodelUser=ViewModelProviders.of(this).get(ViewModelUser ::class.java)
         viewmodel.getAllPharmacie().observe(this, Observer { Pharmacie ->Pharmacie?.let {
             Log.e("dddd",it.toString())
         }

         })
     //onSupportNavigateUp()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = findNavController(R.id.fragment)
        return item!!.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp()||navController.navigateUp(drawerLayout)
    }

}
