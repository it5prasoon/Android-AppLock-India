package com.webers.applock.ui.policydialog

import com.webers.applock.data.AppLockerPreferences
import com.webers.applock.ui.RxAwareViewModel
import javax.inject.Inject

class PrivacyPolicyViewModel @Inject constructor(val appLockerPreferences: AppLockerPreferences) :
    RxAwareViewModel() {

    fun acceptPrivacyPolicy() {
        appLockerPreferences.acceptPrivacyPolicy()
    }

}