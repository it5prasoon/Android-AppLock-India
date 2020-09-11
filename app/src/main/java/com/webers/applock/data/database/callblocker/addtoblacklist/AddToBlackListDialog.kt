package com.webers.applock.data.database.callblocker.addtoblacklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import com.webers.applock.R
import com.webers.applock.databinding.DialogCallBlockerAddToBlacklistBinding
import com.webers.applock.ui.BaseBottomSheetDialog
import com.webers.applock.util.delegate.inflate
import kotlinx.android.synthetic.main.dialog_call_blocker_add_to_blacklist.*

class AddToBlackListDialog : BaseBottomSheetDialog<AddToBlackListViewModel>() {

    private val binding: DialogCallBlockerAddToBlacklistBinding by inflate(R.layout.dialog_call_blocker_add_to_blacklist)

    override fun getViewModel(): Class<AddToBlackListViewModel> =
        AddToBlackListViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonBlock.setOnClickListener {
            if (validateInputFields()) {
                viewModel.blockNumber(
                    editTextName.text.toString(),
                    editTextPhoneNumber.text.toString()
                )
            }
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getViewStateLiveData()
            .observe(this, Observer {
                dismiss()
            })
    }

    private fun validateInputFields(): Boolean {
        return !binding.editTextPhoneNumber.text.isNullOrEmpty()
    }

    companion object {

        fun newInstance(): AppCompatDialogFragment = AddToBlackListDialog()
    }
}