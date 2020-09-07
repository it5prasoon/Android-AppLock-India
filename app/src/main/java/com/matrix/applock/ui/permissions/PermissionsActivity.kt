package com.matrix.applock.ui.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.matrix.applock.ui.BaseActivity
import android.provider.Settings
import com.matrix.applock.R
import com.matrix.applock.databinding.ActivityPermissionsBinding
import com.matrix.applock.service.notification.ServiceNotificationManager
import com.matrix.applock.util.extensions.toast
import javax.inject.Inject

class PermissionsActivity : BaseActivity<PermissionsViewModel>() {

    private lateinit var binding: ActivityPermissionsBinding

    @Inject
    lateinit var serviceNotificationManager: ServiceNotificationManager

    override fun getViewModel(): Class<PermissionsViewModel> = PermissionsViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permissions)

        binding.switchUsageAccess.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && PermissionChecker.checkUsageAccessPermission(this).not()) {
                startActivity(IntentHelper.usageAccessIntent())
            }
        }

        binding.switchOverlay.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && PermissionChecker.checkOverlayPermission(this).not()) {
                startActivityForResult(
                    IntentHelper.overlayIntent(packageName),
                    RC_OVERLAY_PERMISSION
                )
            }
        }

        binding.buttonNext.setOnClickListener {
            if (PermissionChecker.isAllPermissionChecked(this).not()) {
                toast(R.string.permission_toast_enable_permissions)
            }
        }

        binding.textViewPrivacyPolicy.setOnClickListener {
            startActivity(IntentHelper.privacyPolicyWebIntent())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_OVERLAY_PERMISSION -> binding.switchOverlay.isChecked =
                Settings.canDrawOverlays(this)
        }

        if (isAllPermissionsPermitted()) {
            serviceNotificationManager.hidePermissionNotification()
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.switchUsageAccess.isChecked = PermissionChecker.checkUsageAccessPermission(this)

        if (isAllPermissionsPermitted()) {
            finish()
        }
    }

    private fun isAllPermissionsPermitted() =
        PermissionChecker.checkUsageAccessPermission(this) &&
                PermissionChecker.checkOverlayPermission(this)

    companion object {
        private const val RC_OVERLAY_PERMISSION = 123

        fun newIntent(context: Context): Intent {
            return Intent(context, PermissionsActivity::class.java)
        }
    }

}