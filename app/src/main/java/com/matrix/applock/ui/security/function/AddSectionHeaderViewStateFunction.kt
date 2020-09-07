package com.matrix.applock.ui.security.function

import com.matrix.applock.R
import com.matrix.applock.ui.security.AppLockItemBaseViewState
import com.matrix.applock.ui.security.AppLockItemHeaderViewState
import com.matrix.applock.ui.security.AppLockItemItemViewState
import io.reactivex.functions.Function

class AddSectionHeaderViewStateFunction :
    Function<List<AppLockItemItemViewState>, List<AppLockItemBaseViewState>> {

    private val recommendedPackages = HashSet<String>().apply {
        add("com.whatsapp")
        add("com.instagram.android")
        add("com.facebook.orca")
        add("com.facebook.katana")
        add("com.google.android.apps.messaging")
        add("org.telegram.messenger")
        add("com.twitter.android")
        add("com.google.android.apps.photos")
        add("com.google.android.apps.docs")
    }

    override fun apply(appItemList: List<AppLockItemItemViewState>): List<AppLockItemBaseViewState> {

        /**
         * Hash for faster access
         */
        val appListHash = HashMap<String, AppLockItemItemViewState>()

        appItemList.forEach { appItem ->
            appListHash[appItem.appData.parsePackageName()] = appItem
        }


        val resultList = arrayListOf<AppLockItemBaseViewState>()

        /**
         * recommended header view state
         */
        val recommendedHeaderViewState =
            AppLockItemHeaderViewState(R.string.header_recommended_to_lock)
        resultList.add(recommendedHeaderViewState)

        recommendedPackages.forEach {
            appListHash[it]?.let { resultList.add(it) }
        }


        /**
         * All apps header view state
         */
        val allAppsHeaderViewState = AppLockItemHeaderViewState(R.string.header_all_apps)
        resultList.add(allAppsHeaderViewState)

        appItemList.forEach {
            if (resultList.contains(it).not()) {
                resultList.add(it)
            }
        }

        return resultList
    }
}