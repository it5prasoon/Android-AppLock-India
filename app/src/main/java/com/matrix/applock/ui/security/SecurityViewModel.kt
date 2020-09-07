package com.matrix.applock.ui.security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matrix.applock.data.AppDataProvider
import com.matrix.applock.data.database.lockedapps.LockedAppEntity
import com.matrix.applock.data.database.lockedapps.LockedAppsDao
import com.matrix.applock.ui.RxAwareViewModel
import com.matrix.applock.ui.security.function.AddSectionHeaderViewStateFunction
import com.matrix.applock.ui.security.function.LockedAppListViewStateCreator
import com.matrix.applock.util.extensions.doOnBackground
import com.matrix.applock.util.extensions.plusAssign
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