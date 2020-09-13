package com.webers.applock.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.webers.applock.data.database.pattern.PatternDao
import com.webers.applock.repository.UserPreferencesRepository
import com.webers.applock.ui.RxAwareViewModel
import com.webers.applock.util.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val patternDao: PatternDao,
    val userPreferencesRepository: UserPreferencesRepository
) : RxAwareViewModel() {

    private val patternCreationNeedLiveData = MutableLiveData<Boolean>()

    private var appLaunchValidated = false

    init {
        disposables += patternDao.isPatternCreated()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { patternCreationNeedLiveData.value = it == 0 }

    }

    fun getPatternCreationNeedLiveData(): LiveData<Boolean> = patternCreationNeedLiveData

    fun onAppLaunchValidated() {
        appLaunchValidated = true
    }

    fun isPrivacyPolicyAccepted() = userPreferencesRepository.isPrivacyPolicyAccepted()

    fun isAppLaunchValidated(): Boolean = appLaunchValidated

    override fun onCleared() {
        super.onCleared()
        appLaunchValidated = false
        userPreferencesRepository.endSession()
    }

}