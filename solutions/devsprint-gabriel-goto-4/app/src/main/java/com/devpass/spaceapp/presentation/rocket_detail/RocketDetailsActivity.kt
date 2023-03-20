package com.devpass.spaceapp.presentation.rocket_detail

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.devpass.spaceapp.R
import com.devpass.spaceapp.databinding.ActivityRocketDetailsBinding
import com.devpass.spaceapp.model.RocketDetail
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRocketDetailsBinding

    private val viewModel: RocketDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbRocketDetailsBackButton.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launchWhenResumed {
            val id = intent.getStringExtra(ARG_ROCKET)
            id?.let {
                viewModel.loadRocketDetails(id)
            }
        }

        renderRocketDetail()
    }


    private fun updateUI(rocketDetail: RocketDetail?) {
        rocketDetail?.let { model ->
            with(binding) {
                tbRocketDetailsTitle.text = model.name
                textViewNameRocketDetails.text = model.name
                textViewDetailsRocketDetails.text = model.description
                Glide.with(baseContext)
                    .load(model.image)
                    .placeholder(android.R.color.transparent)
                    .into(imageViewRocketDetails)
                showLoadingAnim(false)

            }
        }
    }

    private fun showDialogError(error: String?) {
        showLoadingAnim(false)
        val message = error ?: getString(R.string.rocket_details_dialog_error_message)
        AlertDialog.Builder(baseContext)
            .setTitle(R.string.rocket_details_dialog_error_title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.show()
    }

    private fun renderRocketDetail() {
        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is RocketDetailsUiState.Loading -> showLoadingAnim(true)
                is RocketDetailsUiState.Success -> updateUI(uiState.data)
                is RocketDetailsUiState.Error -> showDialogError(uiState.exception.message)
            }
        }
    }

    private fun showLoadingAnim(isVisible: Boolean) {
        binding.progressBarRocketDetails.isVisible = isVisible
    }

    companion object {
        private const val ARG_ROCKET = "ARG_ROCKET"
    }

}
