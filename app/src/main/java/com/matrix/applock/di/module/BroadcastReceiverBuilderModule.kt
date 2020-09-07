package com.matrix.applock.di.module

import com.matrix.applock.ui.callblocker.service.CallReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BroadcastReceiverBuilderModule {

    @ContributesAndroidInjector
    abstract fun callBroadcastReceiver(): CallReceiver

}