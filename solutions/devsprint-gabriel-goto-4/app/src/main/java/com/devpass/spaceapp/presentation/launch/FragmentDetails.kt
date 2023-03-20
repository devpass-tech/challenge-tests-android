package com.devpass.spaceapp.presentation.launch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.devpass.spaceapp.databinding.FragmentDetailsBinding
import com.devpass.spaceapp.presentation.launch_detail.LaunchDetailsActivity

class FragmentDetails : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val detail = arguments?.get(ARG_DETAIL).toString()

        binding?.tvTextCard?.text = detail

        binding?.tvViewMore?.setOnClickListener {
            val intent = Intent(context, LaunchDetailsActivity::class.java).apply {
                putExtra(ARG_DETAIL, detail)
            }
            startActivity(intent)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    companion object {
        private const val ARG_DETAIL = "ARG_DETAIL"

        fun getInstance(detail: String?) = FragmentDetails().apply {
            arguments = bundleOf(ARG_DETAIL to detail)
        }

    }
}
