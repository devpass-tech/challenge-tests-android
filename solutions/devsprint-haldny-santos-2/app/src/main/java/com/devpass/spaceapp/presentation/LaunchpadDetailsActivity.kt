package com.devpass.spaceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.devpass.spaceapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.devpass.spaceapp.databinding.ActivityLaunchpadDetailsBinding

class LaunchpadDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val launchpadDetailsId by lazy {
        intent.extras?.get(FragmentLaunchpad.ARG_LAUNCHPAD_DETAIL_ID).toString()
    }

    private val mapFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
    }

    private val viewModel: LaunchpadDetailViewModel by viewModels()

    private lateinit var map: GoogleMap

    private lateinit var binding: ActivityLaunchpadDetailsBinding

    private lateinit var latLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchpadDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        lifecycleScope.launchWhenResumed {
            viewModel.safeLaunchpadDetailCall(launchpadDetailsId)
        }

        observeUIState()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.tvToolbarTitle
        toolbar.text = resources.getString(R.string.label_launchpad)
        toolbar.gravity = Gravity.CENTER
        binding.includeToolbar.back.setOnClickListener { onBackPressed() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val launchpad = latLng
        val zoomLevel = 15f

        map.addMarker(MarkerOptions().position(launchpad))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(launchpad, zoomLevel))
    }

    private fun observeUIState() {
        viewModel.launchpadDetailUI.observe(this) {
            when(it) {
                is LaunchpadDetailViewModel.LaunchpadDetailUIState.Error -> Unit // TODO()
                is LaunchpadDetailViewModel.LaunchpadDetailUIState.Loading -> Unit // TODO()
                is LaunchpadDetailViewModel.LaunchpadDetailUIState.Success -> {
                    with(binding) {
                        tvLaunchpad.text = it.data.name
                        tvNameLaunchpad.text = it.data.locality
                        tvLaunchSite.text = it.data.region
                        tvLaunchAttempts.text = getString(R.string.launch_attempts, it.data.launchAttempts)
                        tvLaunchSuccesses.text = getString(R.string.launch_successes, it.data.launchSuccesses)
                        includeToolbar.tvToolbarTitle.text = it.data.name
                        updateMap(it.data.latitude, it.data.longitude)
                    }
                }
            }
        }
    }

    private fun updateMap(latitude: Double, longitude: Double) {
        latLng = LatLng(latitude, longitude)
        mapFragment?.getMapAsync(this)
    }
}
