package com.matrix.applock.ui.permissiondialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.matrix.applock.R
import com.matrix.applock.databinding.DialogUsagePermissionBinding
import com.matrix.applock.ui.BaseBottomSheetDialog
import com.matrix.applock.ui.permissiondialog.analytics.PermissionDialogAnayltics
import com.matrix.applock.ui.permissions.IntentHelper
import com.matrix.applock.util.delegate.inflate

class UsageAccessPermissionDialog : BaseBottomSheetDialog<UsageAccessPermissionViewModel>() {

    private val binding: DialogUsagePermissionBinding by inflate(R.layout.dialog_usage_permission)

    override fun getViewModel(): Class<UsageAccessPermissionViewModel> =
        UsageAccessPermissionViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonPermit.setOnClickListener {
            activity?.let { PermissionDialogAnayltics.usagePermissionPermitClicked(it) }
            onPermitClicked()
        }
        binding.buttonCancel.setOnClickListener {
            activity?.let { PermissionDialogAnayltics.usagePermissionCancelClicked(it) }
            dismiss()
        }
        return binding.root
    }

    private fun onPermitClicked() {
        startActivity(IntentHelper.usageAccessIntent())
        dismiss()
    }

    companion object {

        fun newInstance(): AppCompatDialogFragment = UsageAccessPermissionDialog()
    }

}