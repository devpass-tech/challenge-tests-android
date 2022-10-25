package com.devpass.spaceapp.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Launch(
    val name: String,
    val number: String,
    var date: String,
    val status: String,
    val image: String,
    val rocketId: String,
    val details: String,
    val launchpadId: String,
) : Parcelable {
    companion object : DiffUtil.ItemCallback<Launch>() {
        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem == newItem
        }
    }
}
