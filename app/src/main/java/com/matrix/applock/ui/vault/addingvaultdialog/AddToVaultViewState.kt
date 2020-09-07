package com.matrix.applock.ui.vault.addingvaultdialog

import android.content.Context
import com.matrix.app.security.applocker.R

data class AddToVaultViewState(val progress: Int, val processState: ProcessState) {

    fun getPercentText(context: Context): String {
        return context.getString(R.string.dialog_action_process, progress)
    }
}