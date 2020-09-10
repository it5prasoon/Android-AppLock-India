package com.matrix.applock.ui.rateus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.matrix.applock.R
import com.matrix.applock.databinding.DialogRateUsBinding
import com.matrix.applock.ui.BaseBottomSheetDialog
import com.matrix.applock.util.delegate.inflate

class RateUsDialog : BaseBottomSheetDialog<RateUsViewModel>() {

    private val binding: DialogRateUsBinding by inflate(R.layout.dialog_rate_us)

    override fun getViewModel(): Class<RateUsViewModel> = RateUsViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonRate.setOnClickListener {
            onRatePositiveClicked()
        }
        binding.buttonLater.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun onRatePositiveClicked() {
        viewModel.setUserRateUs()
        activity?.let {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("")
                )
            )
        }
        dismiss()
    }

    companion object {

        fun newInstance(): AppCompatDialogFragment = RateUsDialog()
    }

}