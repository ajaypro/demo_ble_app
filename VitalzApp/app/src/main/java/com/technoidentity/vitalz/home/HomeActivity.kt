package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.ActivityMainBinding
import com.technoidentity.vitalz.utils.ConnectionType
import com.technoidentity.vitalz.utils.NetworkUtil
import com.technoidentity.vitalz.utils.isTablet
import com.technoidentity.vitalz.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var networkMonitor: NetworkUtil

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences(Constants.PREFERENCE_NAME, 0)
        if (isTablet(this)) {
            //shared preferences for tablet
            preferences.edit().putString(Constants.TABLET, "Tab").apply()
            showToast(this, "This is tablet")
        } else {
            preferences.edit().putString(Constants.MOBILE, "Mobile").apply()
            showToast(this, "This is mobile")
        }

        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.addDeviceFragment) // Add fragments ID that should not have up button
            .build()

//        val navController = findNavController(R.id.navHostFragment)
//        setupActionBarWithNavController(this, navController, appBarConfiguration)

        if(isTablet(this)) showToast(this, "This is tablet") else showToast(this, "This is mobile")

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
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.navHostFragment)
//        return navController.navigateUp()
//    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}