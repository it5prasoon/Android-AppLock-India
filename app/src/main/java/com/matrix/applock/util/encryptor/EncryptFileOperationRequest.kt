package com.matrix.applock.util.encryptor

import com.matrix.applock.util.helper.file.DirectoryType
import com.matrix.applock.util.helper.file.FileExtension

data class EncryptFileOperationRequest(
    val fileName: String,
    val fileExtension: FileExtension,
    val directoryType: DirectoryType
)