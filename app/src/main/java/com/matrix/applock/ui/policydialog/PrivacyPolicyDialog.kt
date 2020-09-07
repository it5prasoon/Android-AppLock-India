package com.matrix.applock.ui.policydialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.matrix.applock.R
import com.matrix.applock.databinding.DialogPrivacyPolicyBinding
import com.matrix.applock.ui.BaseBottomSheetDialog
import com.matrix.applock.ui.permissions.IntentHelper
import com.matrix.applock.ui.policydialog.analytics.PrivacyPolicyAnalytics
import com.matrix.applock.util.delegate.inflate

class PrivacyPolicyDialog : BaseBottomSheetDialog<PrivacyPolicyViewModel>() {

    private val binding: DialogPrivacyPolicyBinding by inflate(R.layout.dialog_privacy_policy)

    override fun getViewModel(): Class<PrivacyPolicyViewModel> = PrivacyPolicyViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonAccept.setOnClickListener {
            activity?.let { PrivacyPolicyAnalytics.sendPrivacyPolicyAccept(it) }
            viewModel.acceptPrivacyPolicy()
            dismiss()
        }

        binding.textViewPrivacyPolicy.setOnClickListener {
            startActivity(IntentHelper.privacyPolicyWebIntent())
        }

        return binding.root
    }

    companion object {
        fun newInstance(): AppCompatDialogFragment {
            val dialog = PrivacyPolicyDialog()
            dialog.isCancelable = false
            return dialog
        }
    }

}