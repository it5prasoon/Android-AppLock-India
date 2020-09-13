package com.webers.applock.di.module

import com.webers.applock.di.scope.FragmentScope
import com.webers.applock.ui.background.BackgroundsFragment
import com.webers.applock.ui.callblocker.blacklist.BlackListFragment
import com.webers.applock.ui.callblocker.log.CallLogFragment
import com.webers.applock.ui.security.SecurityFragment
import com.webers.applock.ui.settings.SettingsFragment
import com.webers.applock.ui.vault.vaultlist.VaultListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun securityFragment(): SecurityFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun backgroundFragment(): BackgroundsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun vaultListFragment(): VaultListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun blackListFragment(): BlackListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun callLogFragment(): CallLogFragment

}