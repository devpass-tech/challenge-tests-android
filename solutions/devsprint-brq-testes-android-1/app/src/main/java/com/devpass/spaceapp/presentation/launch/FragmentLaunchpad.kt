
package com.devpass.spaceapp.presentation.launch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devpass.spaceapp.databinding.FragmentLaunchpadBinding
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailUIState
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailViewModel
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLaunchpad : Fragment() {

    private var _binding: FragmentLaunchpadBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LaunchpadDetailViewModel by viewModel()

    private val launchpadDetailsId by lazy {
        arguments?.get(ARG_LAUNCHPAD_DETAIL_ID).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchpadBinding.inflate(inflater, container, false)

        lifecycleScope.launchWhenResumed {
            viewModel.safeLaunchpadDetailCall(launchpadDetailsId)
        }

        observeUIState()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvViewMoreLaunchpad.setOnClickListener {
            val intent = Intent(context, LaunchpadDetailsActivity::class.java).apply {
                putExtra(ARG_LAUNCHPAD_DETAIL_ID, launchpadDetailsId)
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUIState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is LaunchpadDetailUIState.Error -> Unit // TODO()
                is LaunchpadDetailUIState.Loading -> Unit // TODO()
                is LaunchpadDetailUIState.Success -> {
                    with(binding) {
                        tvTittleCardLaunchpad.text = it.data.name
                        tvTypeLaunchpad.text = it.data.locality
                        tvStateLaunchpad.text = it.data.region
                    }
                }
            }
        }
    }

    companion object {
        const val ARG_LAUNCHPAD_DETAIL_ID = "_arg_id"
        fun getInstance(id: String?) : FragmentLaunchpad {
            return FragmentLaunchpad().apply {
                arguments = bundleOf(ARG_LAUNCHPAD_DETAIL_ID to id)
            }
        }
    }
}