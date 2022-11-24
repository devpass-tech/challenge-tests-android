package com.devpass.spaceapp.presentation.launch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.devpass.spaceapp.databinding.FragmentRocketBinding
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsActivity
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsUiState
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketFragment : Fragment() {

    private lateinit var binding: FragmentRocketBinding

    private val viewModel: RocketDetailsViewModel by viewModel()

    private var rocketId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketBinding.inflate(inflater, container, false)

        rocketId = arguments?.get(ARG_ROCKET).toString()

        Log.i("RocketFragment", rocketId)

        lifecycleScope.launchWhenResumed {
            viewModel.loadRocketDetails(rocketId)
        }

        with(binding) {
            cardView.setOnClickListener {
                val intent = Intent(requireContext(), RocketDetailsActivity::class.java).apply {
                    putExtra(ARG_ROCKET, rocketId)
                }
                startActivity(intent)
            }
        }

        observeUIState()

        return binding.root
    }

    private fun observeUIState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is RocketDetailsUiState.Error -> ""
                RocketDetailsUiState.Loading -> ""
                is RocketDetailsUiState.Success -> {
                    binding.tvTextCardRocket.text = it.data?.description
                    binding.tvTitleCardRocket.text = it.data?.name
                    Glide.with(binding.root.context).load(it.data?.image).into(binding.ivImageCard)
                }
            }
        }
    }

    companion object {
        private const val ARG_ROCKET = "ARG_ROCKET"

        fun getInstance(rocketDetail: String?) = RocketFragment().apply {
            arguments = bundleOf(ARG_ROCKET to rocketDetail)
        }

    }

}
