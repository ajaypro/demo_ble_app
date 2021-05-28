package com.technoidentity.vitalz.home

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.utils.ConnectionType
import com.technoidentity.vitalz.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var networkMonitor: NetworkUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTabletDevice(this)

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

    private fun getPhoneModel(): String {
        Log.v("Check","Stage Which Device ${Build.MODEL}")
        return Build.MODEL
    }

    private fun isTabletDevice(activityContext: Context): Boolean {
        val device_large = activityContext.resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK ===
                Configuration.SCREENLAYOUT_SIZE_LARGE
        if (device_large) {
            val metrics = DisplayMetrics()
            val activity = activityContext as Activity
            activity.windowManager.defaultDisplay.getMetrics(metrics)
            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH || metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM || metrics.densityDpi == DisplayMetrics.DENSITY_TV || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                Toast.makeText(
                this, "Detected... You're using a Tab Phone", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        Toast.makeText(
                this, "Detected... You're using a Mobile Phone", Toast.LENGTH_SHORT).show()
        return false
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