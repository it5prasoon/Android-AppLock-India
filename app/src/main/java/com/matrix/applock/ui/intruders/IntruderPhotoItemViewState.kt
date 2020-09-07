package com.matrix.applock.ui.intruders

import com.matrix.applock.util.binding.ImageSize
import java.io.File

data class IntruderPhotoItemViewState(val file: File) {

    fun getFilePath() = file.absolutePath

    fun getImageSize(): ImageSize = ImageSize.MEDIUM
}