package com.matrix.applock.ui.callblocker.blacklist

import android.content.Context
import com.matrix.applock.R
import com.matrix.applock.data.database.callblocker.blacklist.BlackListItemEntity

data class BlackListItemViewState(val blackListItemEntity: BlackListItemEntity) {

    fun getFirstLetter(context: Context): String {
        val unknownNumber = context.getString(R.string.title_name_unknown_number)
        return if (blackListItemEntity.userName.isNullOrEmpty()) {
            unknownNumber.substring(0, 1).toUpperCase()
        } else {
            blackListItemEntity.userName.substring(0, 1).toUpperCase()
        }
    }

    fun getName(context: Context): String {
        val unknownNumber = context.getString(R.string.title_name_unknown_number)
        return if (blackListItemEntity.userName.isNullOrEmpty()) {
            unknownNumber
        } else {
            blackListItemEntity.userName
        }
    }

    fun getNumber(): String {
        return blackListItemEntity.phoneNumber
    }
}