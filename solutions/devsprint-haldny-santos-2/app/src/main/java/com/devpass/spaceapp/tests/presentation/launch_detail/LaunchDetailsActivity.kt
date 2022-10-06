package com.devpass.spaceapp.tests.presentation.launch_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devpass.spaceapp.databinding.ActivityLaunchDetailsBinding

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
