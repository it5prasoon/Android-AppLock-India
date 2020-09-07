package com.matrix.applock.ui.rateus

import com.matrix.applock.repository.UserPreferencesRepository
import com.matrix.applock.ui.RxAwareViewModel
import javax.inject.Inject

class RateUsViewModel @Inject constructor(val userPreferencesRepository: UserPreferencesRepository) :
    RxAwareViewModel() {

    fun setUserRateUs() = userPreferencesRepository.setUserRateUs()
}