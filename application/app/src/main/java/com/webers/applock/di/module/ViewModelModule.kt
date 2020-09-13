package com.webers.applock.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.webers.applock.data.database.callblocker.addtoblacklist.AddToBlackListViewModel
import com.webers.applock.di.ViewModelFactory
import com.webers.applock.di.key.ViewModelKey
import com.webers.applock.ui.background.BackgroundsActivityViewModel
import com.webers.applock.ui.background.BackgroundsFragmentViewModel
import com.webers.applock.ui.browser.BrowserViewModel
import com.webers.applock.ui.browser.bookmarks.BookmarksViewModel
import com.webers.applock.ui.callblocker.CallBlockerViewModel
import com.webers.applock.ui.callblocker.blacklist.BlackListViewModel
import com.webers.applock.ui.callblocker.blacklist.delete.BlackListItemDeleteViewModel
import com.webers.applock.ui.callblocker.log.CallLogViewModel
import com.webers.applock.ui.intruders.IntrudersPhotosViewModel
import com.webers.applock.ui.main.MainViewModel
import com.webers.applock.ui.newpattern.CreateNewPatternViewModel
import com.webers.applock.ui.overlay.activity.OverlayValidationViewModel
import com.webers.applock.ui.permissiondialog.UsageAccessPermissionViewModel
import com.webers.applock.ui.permissions.PermissionsViewModel
import com.webers.applock.ui.policydialog.PrivacyPolicyViewModel
import com.webers.applock.ui.rateus.RateUsViewModel
import com.webers.applock.ui.security.SecurityViewModel
import com.webers.applock.ui.settings.SettingsViewModel
import com.webers.applock.ui.vault.VaultViewModel
import com.webers.applock.ui.vault.addingvaultdialog.AddToVaultViewModel
import com.webers.applock.ui.vault.removingvaultdialog.RemoveFromVaultViewModel
import com.webers.applock.ui.vault.vaultlist.VaultListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(OverlayValidationViewModel::class)
    abstract fun provideFingerPrintOverlayViewModel(overlayValidationViewModel: OverlayValidationViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CreateNewPatternViewModel::class)
    abstract fun provideCreateNewPatternViewModel(createNewPatternViewModel: CreateNewPatternViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(PermissionsViewModel::class)
    abstract fun providePermissionsViewModel(permissionsViewModel: PermissionsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainVieWModel(mainViewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BackgroundsActivityViewModel::class)
    abstract fun provideBackgroundActivityViewModel(backgroundsActivityViewModel: BackgroundsActivityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SecurityViewModel::class)
    abstract fun provideSecurityVieWModel(securityViewModel: SecurityViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BrowserViewModel::class)
    abstract fun provideBrowserViewModel(browserViewModel: BrowserViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BackgroundsFragmentViewModel::class)
    abstract fun provideBackgroundViewModel(backgroundsFragmentViewModel: BackgroundsFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SettingsViewModel::class)
    abstract fun provideSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(RateUsViewModel::class)
    abstract fun provideRateUsViewModel(rateUsViewModel: RateUsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(UsageAccessPermissionViewModel::class)
    abstract fun provideUsageAccessPermissionViewModel(permissionsViewModel: PermissionsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(PrivacyPolicyViewModel::class)
    abstract fun providePrivacyPolicyViewModel(privacyPolicyViewModel: PrivacyPolicyViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BookmarksViewModel::class)
    abstract fun provideBookmarksViewModel(bookmarksViewModel: BookmarksViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(VaultViewModel::class)
    abstract fun provideVaultViewModel(vaultViewModel: VaultViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(VaultListViewModel::class)
    abstract fun provideVaultListViewModel(vaultListViewModel: VaultListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AddToVaultViewModel::class)
    abstract fun provideAddToVaultViewModel(addToVaultViewModel: AddToVaultViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(RemoveFromVaultViewModel::class)
    abstract fun provideRemoveFromVaultViewModel(removeFromVaultViewModel: RemoveFromVaultViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CallBlockerViewModel::class)
    abstract fun provideCallBlockerViewModel(callBlockerViewModel: CallBlockerViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BlackListViewModel::class)
    abstract fun provideBlackListViewModel(blackListViewModel: BlackListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CallLogViewModel::class)
    abstract fun provideCallLogViewModel(callLogViewModel: CallLogViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(AddToBlackListViewModel::class)
    abstract fun provideAddToBlackListViewModel(addToBlackListViewModel: AddToBlackListViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(BlackListItemDeleteViewModel::class)
    abstract fun provideDeleteBlackListItemViewModel(blackListItemDeleteViewModel: BlackListItemDeleteViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(IntrudersPhotosViewModel::class)
    abstract fun provideIntrudersPhotosViewModel(intrudersPhotosViewModel: IntrudersPhotosViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

