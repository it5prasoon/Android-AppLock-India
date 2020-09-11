package com.matrix.applock.service.stateprovider

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.matrix.applock.ui.permissions.PermissionChecker
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PermissionCheckerObservable @Inject constructor(val context: Context) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun get(): Flowable<Boolean> {
        return Flowable.interval(30, TimeUnit.MINUTES)
            .map { PermissionChecker.checkUsageAccessPermission(context).not() }
    }
}