package com.technoidentity.vitalz.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentBleoperationsBinding
import com.technoidentity.vitalz.utils.*
import java.text.SimpleDateFormat
import java.util.*

class BleOperationsFragment: Fragment() {

    private lateinit var device: BluetoothDevice
    private val dateFormatter = SimpleDateFormat("MMM d, HH:mm:ss", Locale.US)
    private val characteristics by lazy {
        ConnectionManager.servicesOnDevice(device)?.flatMap { service ->
            service.characteristics ?: listOf()
        } ?: listOf()
    }

    lateinit var binding: FragmentBleoperationsBinding
    private val characteristicProperties by lazy {
        characteristics.map { characteristic ->
            characteristic to mutableListOf<CharacteristicProperty>().apply {
                if (characteristic.isNotifiable()) add(CharacteristicProperty.Notifiable)
                if (characteristic.isIndicatable()) add(CharacteristicProperty.Indicatable)
                if (characteristic.isReadable()) add(CharacteristicProperty.Readable)
                if (characteristic.isWritable()) add(CharacteristicProperty.Writable)
                if (characteristic.isWritableWithoutResponse()) {
                    add(CharacteristicProperty.WritableWithoutResponse)
                }
            }.toList()
        }.toMap()
    }
    private val characteristicAdapter: CharacteristicAdapter by lazy {
        CharacteristicAdapter(characteristics) { characteristic ->
            showCharacteristicOptions(characteristic)
        }
    }
    private var notifyingCharacteristics = mutableListOf<UUID>()

    override fun onCreate(savedInstanceState: Bundle?) {
        ConnectionManager.registerListener(connectionEventListener)
        super.onCreate(savedInstanceState)
        device = arguments?.getParcelable(BluetoothDevice.EXTRA_DEVICE)
            ?: error(getString(R.string.error_with_device))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBleoperationsBinding.bind(LayoutInflater.from(context).inflate(R.layout.fragment_bleoperations,container, false))
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.characteristicsRecyclerView.apply {
            adapter = characteristicAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
            isNestedScrollingEnabled = false
        }

        val animator = binding.characteristicsRecyclerView.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

    private fun log(message: String) {
        val formattedMessage = String.format("%s: %s", dateFormatter.format(Date()), message)
            val currentLogText = if (binding.logTextView.text.isEmpty()) {
                "Beginning of log."
            } else {
                binding.logTextView.text
            }
            binding.logTextView.text = "$currentLogText\n$formattedMessage"
            binding.logScrollView.fullScroll(View.FOCUS_DOWN)

    }

    override fun onPause() {
        ConnectionManager.unregisterListener(connectionEventListener).also {
            showToast(context, "listerner unregistered")
        }
        ConnectionManager.teardownConnection(device).also {
            showToast(context, "device disconnected")
        }
        super.onPause()
        showAlert(requireActivity(), "Device disconnected", " Device will be disconnected",
            "OK", DialogInterface.OnClickListener{ dialog, which ->
               requireActivity().onBackPressed()
            })

    }

//    override fun onStop() {
//        ConnectionManager.unregisterListener(connectionEventListener).also {
//            showToast(context, "listerner unregistered")
//        }
//        ConnectionManager.teardownConnection(device).also {
//            showToast(context, "device disconnected")
//        }
//        super.onStop()
//    }

//    override fun onDestroy() {
//        ConnectionManager.unregisterListener(connectionEventListener)
//        ConnectionManager.teardownConnection(device)
//        super.onDestroy()
//    }

    private fun showCharacteristicOptions(characteristic: BluetoothGattCharacteristic) {
        characteristicProperties[characteristic]?.let { properties ->
            properties.map { it.action }
            properties.forEach {

                when (it) {
                    CharacteristicProperty.Readable -> {
                        log("Reading from ${characteristic.uuid}")
                        ConnectionManager.readCharacteristic(device, characteristic)
                    }
                    CharacteristicProperty.Writable, CharacteristicProperty.WritableWithoutResponse -> {
                        showWritePayloadDialog(characteristic)
                    }
                    CharacteristicProperty.Notifiable, CharacteristicProperty.Indicatable -> {
                        if (notifyingCharacteristics.contains(characteristic.uuid)) {
                            log("Disabling notifications on ${characteristic.uuid}")
                            ConnectionManager.disableNotifications(device, characteristic)
                        } else {
                            log("Enabling notifications on ${characteristic.uuid}")
                            ConnectionManager.enableNotifications(device, characteristic)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showWritePayloadDialog(characteristic: BluetoothGattCharacteristic) {
//        val hexField = layoutInflater.inflate(R.layout.edittext_hex_payload, null) as EditText
//        alert {
//            customView = hexField
//            isCancelable = false
//            yesButton {
//                with(hexField.text.toString()) {
//                    if (isNotBlank() && isNotEmpty()) {
//                        val bytes = hexToBytes()
//                        log("Writing to ${characteristic.uuid}: ${bytes.toHexString()}")
//                        ConnectionManager.writeCharacteristic(device, characteristic, bytes)
//                    } else {
//                        log("Please enter a hex payload to write to ${characteristic.uuid}")
//                    }
//                }
//            }
//            noButton {}
//        }.show()
//        hexField.showKeyboard()
    }

    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {
            onDisconnect = {
                activity?.runOnUiThread {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Disconnected")
                        .setMessage("Disconnected from device.")
                        .setPositiveButton("ok") { _, _ ->
                            requireActivity().onBackPressed()
                        }
                        .show()

                }
                showToast(context, "Device disconnected")
            }

            onCharacteristicRead = { _, characteristic ->
                log("Read from ${characteristic.uuid}: ${characteristic.value.toHexString()}")
            }

            onCharacteristicWrite = { _, characteristic ->
                log("Wrote to ${characteristic.uuid}")
            }

            onCharacteristicChanged = { _, characteristic ->
                log("Value changed on ${characteristic.uuid}: ${characteristic.value.toHexString()}")
            }

            onNotificationsEnabled = { _, characteristic ->
                log("Enabled notifications on ${characteristic.uuid}")
                notifyingCharacteristics.add(characteristic.uuid)
            }

            onNotificationsDisabled = { _, characteristic ->
                log("Disabled notifications on ${characteristic.uuid}")
                notifyingCharacteristics.remove(characteristic.uuid)
            }
        }
    }

    private enum class CharacteristicProperty {
        Readable,
        Writable,
        WritableWithoutResponse,
        Notifiable,
        Indicatable;

        val action
            get() = when (this) {
                Readable -> "Read"
                Writable -> "Write"
                WritableWithoutResponse -> "Write Without Response"
                Notifiable -> "Toggle Notifications"
                Indicatable -> "Toggle Indications"
            }
    }


}