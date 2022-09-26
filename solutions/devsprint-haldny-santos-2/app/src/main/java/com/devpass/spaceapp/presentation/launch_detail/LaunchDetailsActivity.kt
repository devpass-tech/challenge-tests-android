package com.devpass.spaceapp.presentation.launch_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ActivityLaunchDetailsBinding
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding

class LaunchDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRunBack.setOnClickListener { finish() }
        binding.tvDescription.text = intent.getStringExtra(ARG_DETAIL)
    }

    companion object {
        private const val ARG_DETAIL = "ARG_DETAIL"
    }

}
