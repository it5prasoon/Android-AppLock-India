package com.webers.applock.util.encryptor

import com.webers.applock.util.helper.file.DirectoryType
import com.webers.applock.util.helper.file.FileExtension

data class EncryptFileOperationRequest(
    val fileName: String,
    val fileExtension: FileExtension,
    val directoryType: DirectoryType
)