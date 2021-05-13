package com.technoidentity.vitalz.hospital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.data.datamodel.hospital.HospitalListDataItem
import com.technoidentity.vitalz.databinding.RecyclerViewHospitalListBinding

class HospitalAdapter : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    inner class HospitalViewHolder(val binding: RecyclerViewHospitalListBinding) : RecyclerView.ViewHolder(binding.root)

     private val diffCallBack = object : DiffUtil.ItemCallback<HospitalListDataItem>() {
         override fun areItemsTheSame(
             oldItem: HospitalListDataItem,
             newItem: HospitalListDataItem
         ): Boolean {
             return oldItem.id == newItem.id
         }

         override fun areContentsTheSame(
             oldItem: HospitalListDataItem,
             newItem: HospitalListDataItem
         ): Boolean {
             return oldItem == newItem
         }
     }
        private val differ = AsyncListDiffer(this, diffCallBack)
        private var hospitals: List<HospitalListDataItem>
            get() = differ.currentList
            set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        return HospitalViewHolder(RecyclerViewHospitalListBinding.inflate(LayoutInflater.from(parent.context),
        parent,false))
    }

    override fun getItemCount() = hospitals.size

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.binding.apply {
            val hospital = hospitals[position]
            val fullAddress = (hospital.address.street.toString() + hospital.address.city+
                    hospital.address.state + hospital.address.zipCode)
            tvHospitalName.text = hospital.hospitalName
            tvHospitalId.text = hospital.id
            tvHospitalAddress.text = fullAddress
        }
    }
}
