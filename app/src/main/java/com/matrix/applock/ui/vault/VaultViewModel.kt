package com.matrix.applock.ui.vault

import com.matrix.applock.repository.UserPreferencesRepository
import com.matrix.applock.ui.RxAwareViewModel
import javax.inject.Inject

class VaultViewModel @Inject constructor(val userPreferencesRepository: UserPreferencesRepository) :
    RxAwareViewModel() {

    fun shouldShowRateUs(): Boolean {
        return userPreferencesRepository.isUserRateUs().not()
    }

    fun setRateUsAsked() {
        userPreferencesRepository.setRateUsAsked()
    }
}