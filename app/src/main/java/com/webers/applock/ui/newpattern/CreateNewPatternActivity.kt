package com.webers.applock.ui.newpattern

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.andrognito.patternlockview.PatternLockView
import com.webers.applock.R
import com.webers.applock.databinding.ActivityCreateNewPatternBinding
import com.webers.applock.ui.BaseActivity
import com.webers.applock.ui.permissions.IntentHelper
import com.webers.applock.ui.permissions.PermissionChecker
import com.webers.applock.ui.permissions.PermissionsActivity
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*


class CreateNewPatternActivity : BaseActivity<CreateNewPatternViewModel>() {

    private lateinit var binding: ActivityCreateNewPatternBinding

    override fun getViewModel(): Class<CreateNewPatternViewModel> =
        CreateNewPatternViewModel::class.java

    private var ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE: Int = 5469

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_new_pattern)

        // For overlay permissions
        checkPermission()
        onDisplayPopupPermission()


        binding.patternLockView.addPatternLockListener(object : SimplePatternListener() {
            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                if (viewModel.isFirstPattern()) {
                    viewModel.setFirstDrawedPattern(pattern)
                } else {
                    viewModel.setRedrawnPattern(pattern)
                }
                binding.patternLockView.clearPattern()
            }
        })

        viewModel.getPatternEventLiveData().observe(this, Observer { viewState ->
            binding.viewState = viewState
            binding.executePendingBindings()

            if (viewState.isCreatedNewPattern()) {
                onPatternCreateCompleted()
            }
        })
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PermissionChecker.checkOverlayPermission(this).not()) {
            startActivityForResult(
                IntentHelper.overlayIntent(packageName),
                ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE
            )
        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            Settings.canDrawOverlays(this)
            setResult(Activity.RESULT_OK)
        }
    }

    private fun onDisplayPopupPermission() {
        if (!isMIUI()) {
            return
        }
        try {
            // MIUI 8
            val localIntent = Intent("miui.intent.action.APP_PERM_EDITOR")
            localIntent.setClassName(
                "com.miui.securitycenter",
                "com.miui.permcenter.permissions.PermissionsEditorActivity"
            )
            localIntent.putExtra("extra_pkgname", packageName)
            startActivity(localIntent)
            return
        } catch (ignore: Exception) {
        }
        try {
            // MIUI 5/6/7
            val localIntent = Intent("miui.intent.action.APP_PERM_EDITOR")
            localIntent.setClassName(
                "com.miui.securitycenter",
                "com.miui.permcenter.permissions.AppPermissionsEditorActivity"
            )
            localIntent.putExtra("extra_pkgname", packageName)
            startActivity(localIntent)
            return
        } catch (ignore: Exception) {
        }
        // Otherwise jump to application details
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }


    private fun isMIUI(): Boolean {
        val device = Build.MANUFACTURER
        if (device == "Xiaomi") {
            try {
                val prop = Properties()
                prop.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
                return prop.getProperty(
                    "ro.miui.ui.version.code",
                    null
                ) != null || prop.getProperty(
                    "ro.miui.ui.version.name",
                    null
                ) != null || prop.getProperty("ro.miui.internal.storage", null) != null
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return false
    }

    private fun onPatternCreateCompleted() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, CreateNewPatternActivity::class.java)
        }
    }
}