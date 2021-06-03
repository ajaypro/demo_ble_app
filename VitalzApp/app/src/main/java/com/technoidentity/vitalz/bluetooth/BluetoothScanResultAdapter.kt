package com.technoidentity.vitalz.bluetooth

import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.ScanResultItemListBinding

class BluetoothScanResultAdapter(val result: List<ScanResult>, val bluetoothScanResultClickListener: BluetoothScanResultClickListener) :
    //ListAdapter<ScanResult, BluetoothScanResultAdapter.ScanResultViewHolder>(DealsDiffCallBack()) {
    RecyclerView.Adapter<BluetoothScanResultAdapter.ScanResultViewHolder>() {

    lateinit var binding: ScanResultItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultViewHolder {

        binding = ScanResultItemListBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.scan_result_item_list, parent, false))
        return ScanResultViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ScanResultViewHolder, position: Int) {
        val item = result[position]
        viewHolder.bindView(item, bluetoothScanResultClickListener)

    }

    inner class ScanResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(scanResult: ScanResult, bluetoothScanResultClickListener: BluetoothScanResultClickListener) {
            binding.apply {
                bleDeviceName.text = scanResult.device.name?: "Unnamed"
                bleMacTxt.text = scanResult.device.address
                bleSignalStrength.text = "${scanResult.rssi}".plus("dBm")
            }
            binding.root.setOnClickListener { bluetoothScanResultClickListener.onClick(scanResult) }
        }
    }

    override fun getItemCount() = result.size
}

class DealsDiffCallBack: DiffUtil.ItemCallback<ScanResult>() {
    override fun areItemsTheSame(oldItem: ScanResult, newItem: ScanResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ScanResult, newItem: ScanResult): Boolean {
        return oldItem.device.address == newItem.device.address
    }
}
class BluetoothScanResultClickListener(val clickListener: (scanResult :ScanResult) -> Unit) {
    fun onClick(scanResult :ScanResult) = clickListener(scanResult)
}