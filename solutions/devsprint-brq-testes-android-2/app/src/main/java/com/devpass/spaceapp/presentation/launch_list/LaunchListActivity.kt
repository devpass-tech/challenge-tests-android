package com.devpass.spaceapp.presentation.launch_list

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.BuildConfig
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.R
import com.devpass.spaceapp.presentation.launch.LaunchActivity
import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter

    private val viewModel: LaunchListViewModel by viewModel()
    private val launchNavigator  by inject<LaunchNavigator>()

    private val alertDialogError by lazy {
        AlertDialog.Builder(this@LaunchListActivity).apply {
            with(this) {
                setTitle(R.string.alert_dialog_error_title)
                setMessage(R.string.alert_dialog_error_message)
                setPositiveButton(
                    R.string.alert_dialog_error_tryagain_button,
                    tryAgainButtonClick()
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if(BuildConfig.DEBUG){
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        setupTitle()
        setupRecycleView()
        observeLaunchList()

    }

    private fun tryAgainButtonClick() = DialogInterface.OnClickListener { _, _ ->
        viewModel.getLaunches()
    }

    private fun setupTitle() {
        with(binding.includeToolbar) {
            tvToolbarTitle.setText(R.string.title_toolbar_launch_list_activity)
            tvToolbarTitle.setTypeface(null, Typeface.BOLD)
            back.visibility = View.GONE
        }
    }

    private fun startLoading() {
        with(binding) {
            lottieRocketLoading.playAnimation()
            lottieRocketLoading.visibility = View.VISIBLE
        }
    }

    private fun observeLaunchList() {
        viewModel.launches.observe(this) {
            when (it) {
                is LaunchListViewModel.LaunchListUIState.Success -> {
                    adapter.submitList(it.data)
                    binding.lottieRocketLoading.visibility = View.GONE
                }
                is LaunchListViewModel.LaunchListUIState.Error -> {
                    binding.lottieRocketLoading.visibility = View.GONE
                    alertDialogError.show()
                }
                is LaunchListViewModel.LaunchListUIState.Loading -> {
                    startLoading()
                }
            }
        }
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter {
            Log.i(TAG, "on click $it")
            launchNavigator.openLaunch(baseContext,it)
//            startActivity(Intent(baseContext, LaunchActivity::class.java).also { i ->
//                i.putExtra(LAUNCH_MODEL, it)
//            })
        }
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val TAG = "LaunchListActivity"
        private const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }
}
