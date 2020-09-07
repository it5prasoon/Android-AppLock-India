package com.matrix.applock.ui.callblocker.blacklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.matrix.applock.data.database.callblocker.blacklist.BlackListItemEntity
import com.matrix.applock.R
import com.matrix.applock.databinding.FragmentCallBlockerBlacklistBinding
import com.matrix.applock.ui.BaseFragment
import com.matrix.applock.ui.callblocker.blacklist.delete.BlackListItemDeleteDialog
import com.matrix.applock.util.delegate.inflate

class BlackListFragment : BaseFragment<BlackListViewModel>() {

    private val binding: FragmentCallBlockerBlacklistBinding by inflate(R.layout.fragment_call_blocker_blacklist)

    private val blackListAdapter = BlackListAdapter()

    override fun getViewModel(): Class<BlackListViewModel> = BlackListViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.recyclerViewBlackList.adapter = blackListAdapter
        blackListAdapter.onItemClicked = this@BlackListFragment::showDeleteDialog
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getViewStateLiveData().observe(this, Observer {
            binding.viewState = it
            binding.executePendingBindings()

            blackListAdapter.setBlackListItems(it.blackListViewStateList)
        })
    }

    private fun showDeleteDialog(blackListItem: BlackListItemEntity) {
        activity?.let {
            BlackListItemDeleteDialog.newInstance(blackListItem.blacklistId)
                .show(it.supportFragmentManager, "")
        }
    }

    companion object {
        fun newInstance() = BlackListFragment()
    }

}