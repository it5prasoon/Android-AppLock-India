package com.webers.applock.ui.intruders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.webers.applock.R
import com.webers.applock.databinding.ActivityIntrudersPhotosBinding
import com.webers.applock.ui.BaseActivity
import com.webers.applock.util.delegate.contentView
import javax.inject.Inject

class IntrudersPhotosActivity : BaseActivity<IntrudersPhotosViewModel>() {

    @Inject
    lateinit var intrudersListAdapter: IntrudersListAdapter

    private val binding: ActivityIntrudersPhotosBinding by contentView(R.layout.activity_intruders_photos)

    override fun getViewModel(): Class<IntrudersPhotosViewModel> =
        IntrudersPhotosViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerViewIntrudersPhotosList.adapter = intrudersListAdapter

        binding.imageViewBack.setOnClickListener { finish() }

        viewModel.getIntruderListViewState().observe(this, Observer {
            intrudersListAdapter.updateIntruderList(it.intruderPhotoItemViewStateList)
            binding.viewState = it
            binding.executePendingBindings()
        })
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, IntrudersPhotosActivity::class.java)
        }
    }
}