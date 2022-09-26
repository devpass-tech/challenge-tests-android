package com.devpass.spaceapp.presentation.launch_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ListItemBinding
import com.devpass.spaceapp.model.Launch

class LaunchListAdapter(private val onItemClick: (Launch) -> Unit) : ListAdapter<Launch, LaunchViewHolder>(
    Launch
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder.from(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LaunchViewHolder(private val binding: ListItemBinding, private val onItemClick: (Launch) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private val imageLaunch = binding.ivLogo
    private val numberLaunch = binding.tvNumber
    private val nameLaunch = binding.tvName
    private val dateLaunch = binding.tvDate
    private val statusLaunch = binding.tvStatus

    private var model: Launch? = null

    init {
        itemView.setOnClickListener {
            model?.let { onItemClick(it) }
        }
    }

    fun bind(model: Launch) {
        this.model = model

        Glide
            .with(binding.root.context)
            .load(model.image)
            .placeholder(R.drawable.crs)
            .circleCrop()
            .into(imageLaunch)


        numberLaunch.text = model.number
        nameLaunch.text = model.name
        dateLaunch.text = model.date
        statusLaunch.text = model.status
    }

    companion object {
        fun from(parent: ViewGroup, onItemClick: (Launch) -> Unit): LaunchViewHolder {
            return LaunchViewHolder(
                ListItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                onItemClick
            )
        }
    }
}
