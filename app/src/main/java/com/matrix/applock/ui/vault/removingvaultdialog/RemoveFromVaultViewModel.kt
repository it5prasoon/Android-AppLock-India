package com.matrix.applock.ui.vault.removingvaultdialog

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.crashlytics.android.Crashlytics
import com.matrix.applock.data.database.vault.VaultMediaEntity
import com.matrix.applock.repository.VaultRepository
import com.matrix.applock.ui.RxAwareAndroidViewModel
import com.matrix.applock.ui.vault.addingvaultdialog.ProcessState
import com.matrix.applock.util.encryptor.CryptoProcess
import com.matrix.applock.util.extensions.plusAssign
import com.matrix.applock.util.helper.file.MediaScannerConnector
import com.matrix.applock.util.helper.progress.FakeProgress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoveFromVaultViewModel @Inject constructor(
    private val app: Application,
    private val vaultRepository: VaultRepository
) : RxAwareAndroidViewModel(app) {

    private val removeFromVaultProcessLiveData = MutableLiveData<CryptoProcess>()

    private val fakeProgress = FakeProgress()

    private val removeFromVaultViewStateLiveData =
        MutableLiveData<RemoveFromVaultViewState>()
            .apply {
                value =
                    RemoveFromVaultViewState(progress = 0, processState = ProcessState.PROCESSING)
            }


    init {
        with(fakeProgress) {
            setOnProgressListener {
                removeFromVaultViewStateLiveData.value =
                    RemoveFromVaultViewState(it, ProcessState.PROCESSING)
            }

            setOnCompletedListener {
                removeFromVaultViewStateLiveData.value =
                    RemoveFromVaultViewState(100, ProcessState.COMPLETE)
            }
        }
    }

    fun removeMediaFromVault(vaultMediaEntity: VaultMediaEntity) {
        val decryptionObservable = vaultRepository
            .removeMediaFromVault(vaultMediaEntity)
            .doOnComplete { refreshFileSystem(vaultMediaEntity.originalPath) }

        disposables += decryptionObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { fakeProgress.start() }
            .subscribe(
                {
                    when (it) {
                        is CryptoProcess.Complete -> fakeProgress.complete()
                    }
                },
                { Crashlytics.logException(it) })
    }

    fun getRemoveFromVaultViewStateLiveData() = removeFromVaultViewStateLiveData

    private fun refreshFileSystem(originalPath: String) {
        MediaScannerConnector.scan(app, originalPath)
    }
}