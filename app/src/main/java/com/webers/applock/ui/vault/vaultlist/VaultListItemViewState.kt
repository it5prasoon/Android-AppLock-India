package com.webers.applock.ui.vault.vaultlist

import com.webers.applock.data.database.vault.VaultMediaEntity

data class VaultListItemViewState(val vaultMediaEntity: VaultMediaEntity) {

    fun getDecryptedCachePath() = vaultMediaEntity.decryptedPreviewCachePath
}