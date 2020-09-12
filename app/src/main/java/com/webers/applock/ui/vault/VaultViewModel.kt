package com.webers.applock.ui.vault

import com.webers.applock.repository.UserPreferencesRepository
import com.webers.applock.ui.RxAwareViewModel
import javax.inject.Inject

class VaultViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    RxAwareViewModel() {

    fun shouldShowRateUs(): Boolean {
        return userPreferencesRepository.isUserRateUs().not()
    }

    fun setRateUsAsked() {
        userPreferencesRepository.setRateUsAsked()
    }
}