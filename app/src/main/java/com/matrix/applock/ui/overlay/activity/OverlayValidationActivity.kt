package com.matrix.applock.ui.overlay.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.andrognito.patternlockview.PatternLockView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.ads.*
import com.matrix.applock.databinding.ActivityOverlayValidationBinding
import com.matrix.applock.ui.BaseActivity
import com.matrix.applock.ui.intruders.camera.FrontPictureLiveData
import com.matrix.applock.ui.intruders.camera.FrontPictureState
import com.matrix.applock.ui.newpattern.SimplePatternListener
import com.matrix.applock.ui.overlay.analytics.OverlayAnalytics
import com.matrix.applock.ui.vault.analytics.VaultAdAnalytics
import com.matrix.applock.util.ads.AdTestDevices
import com.matrix.applock.util.extensions.convertToPatternDot
import com.matrix.applock.util.helper.file.FileManager
import javax.inject.Inject
import com.matrix.applock.R



class OverlayValidationActivity : BaseActivity<OverlayValidationViewModel>() {

    @Inject
    lateinit var fileManager: FileManager

    private lateinit var frontPictureLiveData: FrontPictureLiveData

    private lateinit var binding: ActivityOverlayValidationBinding

    override fun getViewModel(): Class<OverlayValidationViewModel> =
        OverlayValidationViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_overlay_validation)

        intent.getStringExtra(KEY_PACKAGE_NAME)?.let { updateLaunchingAppIcon(it) }

        frontPictureLiveData =
            FrontPictureLiveData(application, viewModel.getIntruderPictureImageFile())

        viewModel.getViewStateObservable().observe(this, Observer {
            binding.patternLockView.clearPattern()
            binding.viewState = it
            binding.executePendingBindings()

            if (it.isDrawnCorrect == true || it.fingerPrintResultData?.isSucces() == true) {
                setResult(Activity.RESULT_OK)
                finish()
            }

            if (it.isDrawnCorrect == false || it.fingerPrintResultData?.isNotSucces() == true) {
                YoYo.with(Techniques.Shake)
                    .duration(700)
                    .playOn(binding.textViewPrompt)

                if (it.isIntrudersCatcherMode) {
                    frontPictureLiveData.takePicture()
                }
            }
        })

        viewModel.getBackgroundDrawableLiveData().observe(this, Observer {
            binding.layoutOverlayMain.background = it.getGradiendDrawable(this)
        })

        binding.patternLockView.addPatternLockListener(object : SimplePatternListener() {
            override fun onComplete(pattern: MutableList<PatternLockView.Dot>?) {
                super.onComplete(pattern)
                pattern?.let { viewModel.onPatternDrawn(it.convertToPatternDot()) }
            }
        })

        frontPictureLiveData.observe(this, Observer {
            when (it) {
                is FrontPictureState.Taken -> OverlayAnalytics.sendIntrudersPhotoTakenEvent(this)
                is FrontPictureState.Error -> OverlayAnalytics.sendIntrudersCameraFailedEvent(this)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.HOME")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        showBannerAd()
    }

    private fun showBannerAd() {
        MobileAds.initialize(this)
        val mAdView = AdView(this).apply {
            adSize = AdSize.BANNER
            adUnitId = getString(R.string.overlay_banner_ad_unit_id)
            adListener = object : AdListener() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    VaultAdAnalytics.bannerAdClicked(this@OverlayValidationActivity)
                }

                override fun onAdFailedToLoad(p0: Int) {
                    super.onAdFailedToLoad(p0)
                    VaultAdAnalytics.bannerAdFailed(this@OverlayValidationActivity)
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    VaultAdAnalytics.bannerAdLoaded(this@OverlayValidationActivity)
                }
            }
        }

        binding.adContainer.addView(mAdView)
        val adRequestBuilder = AdRequest.Builder()
        AdTestDevices.DEVICES.forEach {
            adRequestBuilder.addTestDevice(it)
        }
        mAdView.loadAd(adRequestBuilder.build())
    }

    private fun updateLaunchingAppIcon(appPackageName: String) {
        try {
            val icon = packageManager.getApplicationIcon(appPackageName)
            binding.avatarLock.setImageDrawable(icon)
        } catch (e: PackageManager.NameNotFoundException) {
            binding.avatarLock.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_round_lock_24px
                )
            )
            e.printStackTrace()
        }
    }

    companion object {

        private const val KEY_PACKAGE_NAME = "KEY_PACKAGE_NAME"

        fun newIntent(context: Context, packageName: String): Intent {
            val intent = Intent(context, OverlayValidationActivity::class.java)
            intent.putExtra(KEY_PACKAGE_NAME, packageName)
            return intent
        }
    }

}