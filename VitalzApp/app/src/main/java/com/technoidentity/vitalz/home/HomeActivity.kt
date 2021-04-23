package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.utils.ConnectionType
import com.technoidentity.vitalz.utils.NetworkUtil

class HomeActivity : AppCompatActivity() {

    lateinit var networkMonitor: NetworkUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //Loading UserSelection Fragment after 2 sec of splash screen
        val splash = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                    loadUserSelectionFragment()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        splash.start()
    }

    //Handling User Selection Fragment
    private fun loadUserSelectionFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<UserSelectionFragment>(R.id.fragment)
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