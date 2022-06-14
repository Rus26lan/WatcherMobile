package com.rundgrun.watchermobile.presentation.fm.level

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rundgrun.watchermobile.databinding.ItemFmBinding
import com.rundgrun.watchermobile.domain.models.fluidmesh.FmModule

class FMAdapter : RecyclerView.Adapter<FMAdapter.FMViewHolder>() {

    var fmList: List<FmModule> = emptyList()
        set(value) {
            field = value
            Handler(Looper.getMainLooper()).post {
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FMViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFmBinding.inflate(inflater, parent,false)
        return FMViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FMViewHolder, position: Int) {
        val fm = fmList[position]
        with(holder.binding) {
            name.text = fm.meshID
            level.progress = fm.level
            dB.text = fm.dBm
        }
    }

    override fun getItemCount(): Int = fmList.size

    class FMViewHolder(
        val binding: ItemFmBinding
    ) : RecyclerView.ViewHolder(binding.root)
}


