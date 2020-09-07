package com.matrix.applock.ui.vault.removingvaultdialog

import android.content.Context
import com.matrix.applock.R
import com.matrix.applock.ui.vault.addingvaultdialog.ProcessState

data class RemoveFromVaultViewState(val progress: Int, val processState: ProcessState) {

    fun getPercentText(context: Context): String {
        return context.getString(R.string.dialog_action_process, progress)
    }
}