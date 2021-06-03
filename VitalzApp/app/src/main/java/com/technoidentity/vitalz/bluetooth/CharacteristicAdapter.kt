package com.technoidentity.vitalz.bluetooth

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.RowCharacteristicBinding
import com.technoidentity.vitalz.utils.printProperties

class CharacteristicAdapter(
    private val items: List<BluetoothGattCharacteristic>,
    private val onClickListener: ((characteristic: BluetoothGattCharacteristic) -> Unit)
) : RecyclerView.Adapter<CharacteristicAdapter.CharacteristicViewHolder>() {

    lateinit var binding: RowCharacteristicBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicViewHolder {

        binding = RowCharacteristicBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.row_characteristic, parent, false))
        return CharacteristicViewHolder(binding.root, onClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holderCharacteristic: CharacteristicViewHolder, position: Int) {
        val item = items[position]
        holderCharacteristic.bind(item)
    }

   inner class CharacteristicViewHolder(private val view: View,
                                   private val onClickListener: ((characteristic: BluetoothGattCharacteristic) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(characteristic: BluetoothGattCharacteristic) {

            binding.characteristicUuid.text = characteristic.uuid.toString()
            binding.characteristicProperties.text = characteristic.printProperties()
            binding.root.setOnClickListener { onClickListener.invoke(characteristic) }
        }
    }
}


