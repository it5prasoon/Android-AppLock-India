package com.matrix.applock.ui.intruders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.matrix.app.security.applocker.R
import com.matrix.app.security.applocker.databinding.ActivityIntrudersPhotosBinding
import com.matrix.applock.ui.BaseActivity
import com.matrix.applock.util.delegate.contentView
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