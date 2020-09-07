package com.matrix.applock.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matrix.applock.data.database.pattern.PatternDao
import com.matrix.applock.repository.UserPreferencesRepository
import com.matrix.applock.ui.RxAwareViewModel
import com.matrix.applock.util.extensions.plusAssign
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