package com.webers.applock.ui.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi


object IntentHelper {

    @RequiresApi(Build.VERSION_CODES.M)
    fun overlayIntent(packageName: String): Intent {
        return Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun usageAccessIntent(): Intent {
        return Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
    }

    fun privacyPolicyWebIntent(): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("https://it5prasoon.github.io"))
    }

    fun rateUsIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("")
        )
    }

    fun startStorePage(activity: Activity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("")
                )
            )
        } catch (anfe: android.content.ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("")
                )
            )
        }
    }
}