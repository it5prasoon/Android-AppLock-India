package com.matrix.applock.di.module

import com.matrix.applock.di.scope.ActivityScope
import com.matrix.applock.ui.background.BackgroundsActivity
import com.matrix.applock.ui.browser.BrowserActivity
import com.matrix.applock.ui.callblocker.CallBlockerActivity
import com.matrix.applock.ui.intruders.IntrudersPhotosActivity
import com.matrix.applock.ui.main.MainActivity
import com.matrix.applock.ui.newpattern.CreateNewPatternActivity
import com.matrix.applock.ui.overlay.activity.OverlayValidationActivity
import com.matrix.applock.ui.permissions.PermissionsActivity
import com.matrix.applock.ui.vault.VaultActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by mertsimsek on 12/11/2017.
 */
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun backgroundActivity(): BackgroundsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun browserActivity(): BrowserActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun vaultActivity(): VaultActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun callBlockerActivity(): CallBlockerActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, DialogFragmentBuilderModule::class])
    abstract fun intrudersPhotosActivity(): IntrudersPhotosActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun permissionsActivity(): PermissionsActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun createNewPatternActivity(): CreateNewPatternActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun fingerPrintOverlayActivity(): OverlayValidationActivity
}