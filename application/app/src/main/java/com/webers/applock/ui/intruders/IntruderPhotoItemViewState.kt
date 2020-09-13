package com.webers.applock.ui.intruders

import com.webers.applock.util.binding.ImageSize
import java.io.File

data class IntruderPhotoItemViewState(val file: File) {

    fun getFilePath() = file.absolutePath

    fun getImageSize(): ImageSize = ImageSize.MEDIUM
}