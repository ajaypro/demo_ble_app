package com.technoidentity.vitalz.hospital

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.data.datamodel.patient.PatientDataListItem
import com.technoidentity.vitalz.databinding.RecyclerViewPatientListBinding

class PatientAdapter : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    inner class PatientViewHolder(val binding: RecyclerViewPatientListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<PatientDataListItem>() {
        override fun areItemsTheSame(
            oldItem: PatientDataListItem,
            newItem: PatientDataListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientDataListItem,
            newItem: PatientDataListItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)
    private var patient: List<PatientDataListItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            RecyclerViewPatientListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return patient.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.binding.apply {
            val patient = patient[position]
            tvPatientName.text = patient.name
            tvPatientAge.text = patient.age.toString()
            tvPatientSex.text = patient.gender
            tvPatientId.text = patient.id.toString()
            tvPatientHospitalAddress.text = patient.address
        }
    }
}
