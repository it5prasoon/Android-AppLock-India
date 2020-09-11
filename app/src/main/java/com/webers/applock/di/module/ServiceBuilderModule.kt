package com.webers.applock.di.module

import com.webers.applock.service.AppLockerService
import com.webers.applock.ui.callblocker.service.CallBlockerScreeningService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract fun appLockerService(): AppLockerService

    @ContributesAndroidInjector
    abstract fun callBlockerService(): CallBlockerScreeningService
}