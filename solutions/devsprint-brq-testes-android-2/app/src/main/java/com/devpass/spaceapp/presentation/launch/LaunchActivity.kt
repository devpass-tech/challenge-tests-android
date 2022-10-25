package com.devpass.spaceapp.presentation.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devpass.spaceapp.databinding.ActivityTabBinding
import com.devpass.spaceapp.model.Launch

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabBinding
    private var model: Launch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = intent.getParcelableExtra(LAUNCH_MODEL)

        val fragments = listOf(
            FragmentDetails.getInstance(model?.details),
            RocketFragment.getInstance(model?.rocketId),
            FragmentLaunchpad.getInstance(model?.launchpadId)
        )
        val fragmentsPageTitle = listOf("Details", "Rocket", "Launchpad")
        val viewPagerAdapter = ViewPagerAdapter(
            fragments = fragments,
            fragmentManager = supportFragmentManager,
            tittles = fragmentsPageTitle
        )

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.btRunBack.setOnClickListener { onBackPressed() }

        with(binding) {
            tvTittle.text = model?.name
            tvDate.text = model?.date
            tvStatus.text = model?.status
            Glide.with(this@LaunchActivity).load(model?.image).into(ivImageSpace)

        }
    }

    companion object {
        private const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }

}
