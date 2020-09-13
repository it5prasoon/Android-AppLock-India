package com.webers.applock.di.module

import com.webers.applock.ui.callblocker.service.CallReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BroadcastReceiverBuilderModule {

    @ContributesAndroidInjector
    abstract fun callBroadcastReceiver(): CallReceiver

}