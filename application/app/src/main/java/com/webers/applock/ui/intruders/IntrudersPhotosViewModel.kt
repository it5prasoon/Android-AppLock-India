package com.webers.applock.ui.intruders

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.webers.applock.ui.RxAwareViewModel
import com.webers.applock.util.extensions.plusAssign
import com.webers.applock.util.helper.file.FileExtension
import com.webers.applock.util.helper.file.FileManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Inject

class IntrudersPhotosViewModel @Inject constructor(
    val app: Application,
    val fileManager: FileManager
) :
    RxAwareViewModel() {

    private val intruderListViewState = MutableLiveData<IntrudersViewState>()

    init {
        loadIntruderPhotos()
    }

    fun getIntruderListViewState(): LiveData<IntrudersViewState> =
        intruderListViewState

    private fun loadIntruderPhotos() {
        val subFilesObservable = Single.create<List<File>> {
            val subFiles = fileManager.getSubFiles(
                fileManager.getExternalDirectory(FileManager.SubFolder.INTRUDERS),
                FileExtension.JPEG
            )
            it.onSuccess(subFiles)
        }

        disposables += subFilesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { files ->
                    intruderListViewState.value = IntrudersViewState(mapToViewState(files))
                },
                { error -> Log.v("TEST", "error : ${error.message}") })
    }

    private fun mapToViewState(files: List<File>): List<IntruderPhotoItemViewState> {
        val viewStateList = arrayListOf<IntruderPhotoItemViewState>()
        files.forEach { viewStateList.add((IntruderPhotoItemViewState(it))) }
        return viewStateList
    }


}