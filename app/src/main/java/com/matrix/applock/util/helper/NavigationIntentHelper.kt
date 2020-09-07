package com.matrix.applock.util.helper

import android.content.Intent
import android.net.Uri
import com.matrix.app.security.applocker.BuildConfig


object NavigationIntentHelper {

    fun getShareAppIntent(): Intent {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out app at: https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
        )
        sendIntent.type = "text/plain"
        return sendIntent
    }

    fun getRateAppIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
        )
    }

    fun getFeedbackIntent(): Intent {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "momentolabs@gmail.com", null
            )
        )
        return Intent.createChooser(emailIntent, "Send email...")
    }
}