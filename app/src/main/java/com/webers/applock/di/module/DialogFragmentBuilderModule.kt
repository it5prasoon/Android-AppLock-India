package com.webers.applock.di.module

import com.webers.applock.di.scope.FragmentScope
import com.webers.applock.data.database.callblocker.addtoblacklist.AddToBlackListDialog
import com.webers.applock.ui.browser.bookmarks.BookmarksDialog
import com.webers.applock.ui.callblocker.blacklist.delete.BlackListItemDeleteDialog
import com.webers.applock.ui.permissiondialog.UsageAccessPermissionDialog
import com.webers.applock.ui.policydialog.PrivacyPolicyDialog
import com.webers.applock.ui.rateus.RateUsDialog
import com.webers.applock.ui.vault.addingvaultdialog.AddToVaultDialog
import com.webers.applock.ui.vault.removingvaultdialog.RemoveFromVaultDialog
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