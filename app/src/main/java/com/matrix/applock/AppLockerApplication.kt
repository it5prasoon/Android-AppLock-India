package com.matrix.applock

import android.content.Context
import androidx.multidex.MultiDex
import com.bugsnag.android.Bugsnag
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho
import com.google.android.gms.ads.MobileAds
import com.matrix.applock.service.ServiceStarter
import com.matrix.applock.service.worker.WorkerStarter
import com.raqun.beaverlib.Beaver
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppLockerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this, getString(R.string.mobile_ad_id))
        Stetho.initializeWithDefaults(this)
        Bugsnag.init(this)
        Beaver.build(this)
        ServiceStarter.startService(this)
        SoLoader.init(this, false)
        WorkerStarter.startServiceCheckerWorker()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}