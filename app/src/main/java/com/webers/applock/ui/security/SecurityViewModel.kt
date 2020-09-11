package com.webers.applock.ui.security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.webers.applock.data.AppDataProvider
import com.webers.applock.data.database.lockedapps.LockedAppEntity
import com.webers.applock.data.database.lockedapps.LockedAppsDao
import com.webers.applock.ui.RxAwareViewModel
import com.webers.applock.ui.security.function.AddSectionHeaderViewStateFunction
import com.webers.applock.ui.security.function.LockedAppListViewStateCreator
import com.webers.applock.util.extensions.doOnBackground
import com.webers.applock.util.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SecurityViewModel @Inject constructor(
    appDataProvider: AppDataProvider,
    val lockedAppsDao: LockedAppsDao
) : RxAwareViewModel() {

    private val appDataViewStateListLiveData = MutableLiveData<List<AppLockItemBaseViewState>>()

    init {
        val installedAppsObservable = appDataProvider.fetchInstalledAppList().toObservable()
        val lockedAppsObservable = lockedAppsDao.getLockedApps().toObservable()

        disposables += LockedAppListViewStateCreator.create(
            installedAppsObservable,
            lockedAppsObservable
        )
            .map(AddSectionHeaderViewStateFunction())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                appDataViewStateListLiveData.value = it
            }
    }

    fun getAppDataListLiveData(): LiveData<List<AppLockItemBaseViewState>> =
        appDataViewStateListLiveData

    fun lockApp(appLockItemViewState: AppLockItemItemViewState) {
        disposables += doOnBackground {
            lockedAppsDao.lockApp(
                LockedAppEntity(
                    appLockItemViewState.appData.packageName
                )
            )
        }
    }

    fun unlockApp(appLockItemViewState: AppLockItemItemViewState) {
        disposables += doOnBackground {
            lockedAppsDao.unlockApp(appLockItemViewState.appData.packageName)
        }
    }
}