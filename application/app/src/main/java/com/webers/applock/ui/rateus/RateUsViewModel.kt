package com.webers.applock.ui.rateus

import com.webers.applock.repository.UserPreferencesRepository
import com.webers.applock.ui.RxAwareViewModel
import javax.inject.Inject

class RateUsViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    RxAwareViewModel() {

    fun setUserRateUs() = userPreferencesRepository.setUserRateUs()
}