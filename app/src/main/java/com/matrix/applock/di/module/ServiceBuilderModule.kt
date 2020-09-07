package com.matrix.applock.di.module

import com.matrix.applock.service.AppLockerService
import com.matrix.applock.ui.callblocker.service.CallBlockerScreeningService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract fun appLockerService(): AppLockerService

    @ContributesAndroidInjector
    abstract fun callBlockerService(): CallBlockerScreeningService
}