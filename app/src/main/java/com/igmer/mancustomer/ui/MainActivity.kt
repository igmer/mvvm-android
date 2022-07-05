package com.igmer.mancustomer.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         navView = binding.navView
        // ocupar el menu de la barra de navegacion
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
//                R.id.navigation_sales,
//                R.id.navigation_payment,
                R.id.customerFragment,
                R.id.navigation_products
            )
        )
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
//                R.id.navigation_sales-> showBottomNavigation()
                R.id.customerFragment-> showBottomNavigation()
                R.id.navigation_products-> showBottomNavigation()
//                R.id.navigation_payment-> showBottomNavigation()
                else-> hideBottomNavigation()
            }
        }
       navView.setupWithNavController(
           Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
       )
    }

    private fun hideBottomNavigation() {
        navView.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        navView.visibility = View.VISIBLE
    }

}