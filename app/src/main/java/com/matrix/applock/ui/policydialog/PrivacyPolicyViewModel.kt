package com.matrix.applock.ui.policydialog

import com.matrix.applock.data.AppLockerPreferences
import com.matrix.applock.ui.RxAwareViewModel
import javax.inject.Inject

class PrivacyPolicyViewModel @Inject constructor(val appLockerPreferences: AppLockerPreferences) :
    RxAwareViewModel() {

    fun acceptPrivacyPolicy() {
        appLockerPreferences.acceptPrivacyPolicy()
    }

}