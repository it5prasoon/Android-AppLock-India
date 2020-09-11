package com.webers.applock.ui.vault.addingvaultdialog

import android.content.Context
import com.webers.applock.R

data class AddToVaultViewState(val progress: Int, val processState: ProcessState) {

    fun getPercentText(context: Context): String {
        return context.getString(R.string.dialog_action_process, progress)
    }
}