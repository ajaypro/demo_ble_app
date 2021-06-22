package com.technoidentity.vitalz.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.ActivityMainBinding
import com.technoidentity.vitalz.utils.ConnectionType
import com.technoidentity.vitalz.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var networkMonitor: NetworkUtil

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.navHostFragment)
        //bottomNavigation with Navigation Component
        setUpBottomNavigationBar(navController)

        bottomNavView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home_tab ->{
                    navController.navigate(R.id.notificationsFragment)
                }
                R.id.notifications_tab ->{
                    navController.navigate(R.id.nurseCareTakerDashboardFragment)
                }
                R.id.settings_tab ->{
                    navController.navigate(R.id.doctorDashboardFragment)
                }
            }
        }

        //Adding badges to Notification
        val badge = binding.bottomNavView.getOrCreateBadge(R.id.notifications_tab)
        badge.isVisible = true
        // An icon only badge will be displayed unless a number is set:
        badge.number = 99

        networkMonitor = NetworkUtil(this)

        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.WIFI -> {
                                //internet_status.text = "Wifi Connection"

                            }
                            ConnectionType.CELLULAR -> {
                                //internet_status.text = "Cellular Connection"

                            }
                            else -> {

                            }
                        }
                    }
                    false -> {
                        Snackbar.make(View(this), "No Connection", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

     private fun setUpBottomNavigationBar(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment ||
                destination.id == R.id.userSelectionFragment2 ||
                destination.id == R.id.hospitalListFragment ||
                destination.id == R.id.patientListFragment ||
                destination.id == R.id.careTakerMobileOTPFragment ||
                destination.id == R.id.careTakerMobileLoginFragment ||
                destination.id == R.id.patientProfileFragment

            ){
               bottomNavView.visibility = View.GONE
            }else{
                bottomNavView.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}