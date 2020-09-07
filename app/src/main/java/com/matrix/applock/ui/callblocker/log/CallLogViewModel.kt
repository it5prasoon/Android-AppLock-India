package com.matrix.applock.ui.callblocker.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matrix.applock.repository.CallBlockerRepository
import com.matrix.applock.ui.RxAwareViewModel
import com.matrix.applock.util.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CallLogViewModel @Inject constructor(val callBlockerRepository: CallBlockerRepository) :
    RxAwareViewModel() {

    private val callLogsViewStateLiveData = MutableLiveData<CallLogViewState>()

    init {
        callLogsViewStateLiveData.value = CallLogViewState.empty()

        disposables += callBlockerRepository.getCallLogs()
            .map {
                val itemViewStateList = arrayListOf<CallLogItemViewState>()
                it.forEach { itemViewStateList.add(CallLogItemViewState(it)) }
                CallLogViewState(itemViewStateList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callLogsViewStateLiveData.value = it
            }
    }

    fun getViewStateLiveData(): LiveData<CallLogViewState> = callLogsViewStateLiveData

}