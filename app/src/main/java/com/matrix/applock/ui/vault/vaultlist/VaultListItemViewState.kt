package com.matrix.applock.ui.vault.vaultlist

import com.matrix.applock.data.database.vault.VaultMediaEntity

data class VaultListItemViewState(val vaultMediaEntity: VaultMediaEntity) {

    fun getDecryptedCachePath() = vaultMediaEntity.decryptedPreviewCachePath
}