package com.matrix.applock.ui.vault.removingvaultdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.matrix.app.security.applocker.R
import com.matrix.applock.data.database.vault.VaultMediaEntity
import com.matrix.applock.data.database.vault.VaultMediaType.*
import com.matrix.app.security.applocker.databinding.DialogRemoveFromVaultBinding
import com.matrix.applock.ui.BaseBottomSheetDialog
import com.matrix.applock.ui.vault.addingvaultdialog.ProcessState
import com.matrix.applock.ui.vault.analytics.VaultAnalytics
import com.matrix.applock.util.delegate.inflate

class RemoveFromVaultDialog : BaseBottomSheetDialog<RemoveFromVaultViewModel>() {

    private val binding: DialogRemoveFromVaultBinding by inflate(R.layout.dialog_remove_from_vault)

    override fun getViewModel(): Class<RemoveFromVaultViewModel> =
        RemoveFromVaultViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val vaultMediaEntity = arguments?.getParcelable<VaultMediaEntity>(KEY_VAULT_MEDIA_ENTITY)

        viewModel.getRemoveFromVaultViewStateLiveData().observe(this, Observer { viewState ->
            binding.viewState = viewState
            binding.executePendingBindings()

            if (viewState.processState == ProcessState.COMPLETE) {
                dismiss()

                vaultMediaEntity?.mediaType?.let {
                    when (it) {
                        TYPE_IMAGE.mediaType -> activity?.let { VaultAnalytics.removedImageVault(it) }
                        TYPE_VIDEO.mediaType -> activity?.let { VaultAnalytics.removedVideoVault(it) }
                        else -> {
                        }
                    }
                }
            }
        })

        vaultMediaEntity?.let { viewModel.removeMediaFromVault(it) }
    }

    companion object {
        private const val KEY_VAULT_MEDIA_ENTITY = "KEY_VAULT_MEDIA_ENTITY"

        fun newInstance(vaultMediaEntity: VaultMediaEntity): DialogFragment {
            return RemoveFromVaultDialog().apply {
                isCancelable = false
                arguments = Bundle().apply {
                    putParcelable(KEY_VAULT_MEDIA_ENTITY, vaultMediaEntity)

                }
            }
        }
    }
}