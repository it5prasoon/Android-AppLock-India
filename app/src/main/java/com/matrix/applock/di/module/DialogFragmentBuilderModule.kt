package com.matrix.applock.di.module

import com.iammert.hdwallpapers.di.scope.FragmentScope
import com.matrix.applock.data.database.callblocker.addtoblacklist.AddToBlackListDialog
import com.matrix.applock.ui.browser.bookmarks.BookmarksDialog
import com.matrix.applock.ui.callblocker.blacklist.delete.BlackListItemDeleteDialog
import com.matrix.applock.ui.permissiondialog.UsageAccessPermissionDialog
import com.matrix.applock.ui.policydialog.PrivacyPolicyDialog
import com.matrix.applock.ui.rateus.RateUsDialog
import com.matrix.applock.ui.vault.addingvaultdialog.AddToVaultDialog
import com.matrix.applock.ui.vault.removingvaultdialog.RemoveFromVaultDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogFragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun rateUsDialogFragment(): RateUsDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun permissionDialogFragment(): UsageAccessPermissionDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun privacyPolicyDialogFragment(): PrivacyPolicyDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bookmarksDialogFragment(): BookmarksDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun addToVaultDialog(): AddToVaultDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun addToBlackListDialog(): AddToBlackListDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun deleteBlackListItemDialog(): BlackListItemDeleteDialog

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun removeFromVaultDialog(): RemoveFromVaultDialog
}