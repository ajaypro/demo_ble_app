package com.technoidentity.vitalz.home

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
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
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.base.BaseActivity
import com.technoidentity.vitalz.bluetooth.*
import com.technoidentity.vitalz.databinding.ActivityHomeBinding
import com.technoidentity.vitalz.utils.*
import com.technoidentity.vitalz.utils.Constants.REQUEST_LOCATION_SERVICE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.CompletableDeferred
import timber.log.Timber


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {


    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

     val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var networkMonitor: NetworkUtil
    lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeActivityViewModel by viewModels()
    private val bluetoothAdapter by lazy(LazyThreadSafetyMode.NONE) {
        (getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }

    private val BluetoothAdapter.isDisabled: Boolean
        get() = !isEnabled

    private var locationServiceEnabled: CompletableDeferred<Boolean>? = null
     var locationPermissionGranted: CompletableDeferred<Boolean>? = null
     var bluetoothEnabled: CompletableDeferred<Boolean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
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

        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.homeFragment,R.id.addDeviceFragment, R.id.bleScanResultFragment) // Add fragments ID that should not have up button
            .build()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        lifecycleScope.launchWhenStarted {
            if (initializeBleScanner()) {
                    showToast(
                        this@HomeActivity,
                        "Scanner initialised successfully")
                   Timber.d(sharedViewModel.javaClass.toString())
                    sharedViewModel.startScan()
                }
            else {
                    showToast(
                        this@HomeActivity,
                        "Scanner initialisation failed"
                    )
                }
        }
    }

    private suspend fun initializeBleScanner(): Boolean {
        locationServiceEnabled = CompletableDeferred()
        locationPermissionGranted = CompletableDeferred()
        bluetoothEnabled = CompletableDeferred()

        fun done(result: Boolean = false): Boolean {
            locationServiceEnabled = null
            locationPermissionGranted = null
            bluetoothEnabled = null
            return result
        }

        if (!hasPermission(PERMISSION_TYPE_FINE_LOCATION)) {
            val positiveButtonClick = { _: DialogInterface, _: Int ->
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
                showAlert(
                    title = "Location Permission Required",
                    message = "The application requires location access in order to scan for BLE devices.",
                    positiveBtnClickListener = positiveButtonClick
                )

            if (locationPermissionGranted?.await() == false){
                return done()
            }
        }

        if (!isLocationServiceEnabled()) {
            promptEnableLocationServices(REQUEST_LOCATION_SERVICE)
            if (locationServiceEnabled?.await() == false) return done()
        }

        if (bluetoothAdapter.isDisabled) {
            promptEnableBluetooth()
            if (bluetoothEnabled?.await() == false) return done()
        }

        return done(true)
    }

    private fun promptEnableBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startBleOnForResult.launch(enableBtIntent)
    }

    val startBleOnForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

            when (result.resultCode) {
                RESULT_OK -> bluetoothEnabled?.run {
                    complete(result.resultCode == RESULT_OK)
                }
                else -> {
                        showAlert(
                            title = "Enable Bluetooth",
                            message = "Bluetooth should be enabled to use the app",
                            positiveBtnClickListener = { _, _ ->
                                promptEnableBluetooth()
                            },
                            negativeBtnClickListener = { _, _ ->
                                finishAffinity()
                            })
                }

            }
        }

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {

                locationPermissionGranted?.run {
                    complete(isGranted)
                }

            } else {
                    showAlert(
                        title = "Permission Required",
                        message = "App requires location permission for bluetooth, app will be closed",
                        positiveBtnClickListener = { _, _ ->
                             finishAffinity()
                        })
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
