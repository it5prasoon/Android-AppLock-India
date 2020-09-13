package com.webers.applock.ui.callblocker.blacklist.delete

import com.webers.applock.repository.CallBlockerRepository
import com.webers.applock.ui.RxAwareViewModel
import com.webers.applock.util.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlackListItemDeleteViewModel @Inject constructor(val callBlockerRepository: CallBlockerRepository) :
    RxAwareViewModel() {

    fun deleteFromBlackList(blackListItemId: Int) {
        disposables += callBlockerRepository.deleteBlackListItem(blackListItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}