package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.*
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.ActivityMainBinding
import com.technoidentity.vitalz.hospital.HospitalViewModel
import com.technoidentity.vitalz.utils.ConnectionType
import com.technoidentity.vitalz.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var networkMonitor: NetworkUtil
    lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.navHostFragment)
        //bottomNavigation with Navigation Component
        setUpBottomNavigationBar(navController)

        //setting up bottom Nav with Nav Controller
        binding.bottomNavView.setupWithNavController(navController)


        binding.bottomNavView.setOnNavigationItemSelectedListener {
            lifecycleScope.launchWhenCreated {
                when (it.itemId) {
                    R.id.home_tab -> {
                        homeViewModel.isSelected.collect { status ->
                            when (status) {
                                true -> {
                                    navController.navigate(R.id.nurseCareTakerDashboardFragment)
                                }
                                false -> {
                                    navController.navigate(R.id.doctorDashboardFragment)
                                }
                            }
                        }
                    }
                    R.id.notifications_tab -> {
                        navController.navigate(R.id.notificationsFragment)
                    }
                    R.id.settings_tab -> {
                        navController.navigate(R.id.patientProfileFragment)
                    }
                }
            }
            true
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
                destination.id == R.id.patientProfileFragment ||
                destination.id == R.id.doctorNurseLoginFragment

            ) {
                bottomNavView.visibility = View.GONE
            } else {
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