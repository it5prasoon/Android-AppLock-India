package com.matrix.applock.di.module

import com.matrix.applock.di.scope.FragmentScope
import com.matrix.applock.ui.background.BackgroundsFragment
import com.matrix.applock.ui.callblocker.blacklist.BlackListFragment
import com.matrix.applock.ui.callblocker.log.CallLogFragment
import com.matrix.applock.ui.security.SecurityFragment
import com.matrix.applock.ui.settings.SettingsFragment
import com.matrix.applock.ui.vault.vaultlist.VaultListFragment
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