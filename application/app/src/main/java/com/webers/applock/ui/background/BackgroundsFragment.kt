package com.webers.applock.ui.background

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.webers.applock.R
import com.webers.applock.databinding.FragmentBackgroundsBinding
import com.webers.applock.ui.BaseFragment
import com.webers.applock.ui.background.analytics.BackgroundAnalytics
import com.webers.applock.util.delegate.inflate

class BackgroundsFragment : BaseFragment<BackgroundsFragmentViewModel>() {

    private val binding: FragmentBackgroundsBinding by inflate(R.layout.fragment_backgrounds)

    private val backgroundsAdapter = BackgroundsAdapter()

    override fun getViewModel(): Class<BackgroundsFragmentViewModel> =
        BackgroundsFragmentViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.recyclerViewBackgrounds.adapter = backgroundsAdapter
        backgroundsAdapter.onItemSelected = { onBackgroundItemSelected(it) }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getBackgroundViewStateLiveData().observe(viewLifecycleOwner, Observer {
            backgroundsAdapter.setViewStateList(it)
        })
    }

    private fun onBackgroundItemSelected(selectedItemViewState: GradientItemViewState) {
        viewModel.onSelectedItemChanged(selectedItemViewState)
        activity?.let {
            BackgroundAnalytics.sendBackgroundChangedEvent(
                it,
                selectedItemViewState.id
            )
        }
    }

    companion object {

        fun newInstance(): Fragment = BackgroundsFragment()
    }
}