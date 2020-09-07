package com.matrix.applock.ui.background

import androidx.lifecycle.MutableLiveData
import com.matrix.applock.data.AppLockerPreferences
import com.matrix.applock.ui.RxAwareViewModel
import javax.inject.Inject

class BackgroundsFragmentViewModel @Inject constructor(val appLockerPreferences: AppLockerPreferences) :
    RxAwareViewModel() {

    private val backgroundViewStateLiveData = MutableLiveData<List<GradientItemViewState>>()

    init {
        val selectedBackgroundId = appLockerPreferences.getSelectedBackgroundId()
        val gradientBackViewStateList = GradientBackgroundDataProvider.gradientViewStateList
        gradientBackViewStateList.forEach {
            if (it.id == selectedBackgroundId) {
                it.isChecked = true
            }
        }
        backgroundViewStateLiveData.value = gradientBackViewStateList
    }

    fun getBackgroundViewStateLiveData() = backgroundViewStateLiveData

    fun onSelectedItemChanged(selectedItemViewState: GradientItemViewState) {
        val modifiedList = backgroundViewStateLiveData.value
        modifiedList?.forEach { it.isChecked = it.id == selectedItemViewState.id }
        backgroundViewStateLiveData.value = modifiedList

        appLockerPreferences.setSelectedBackgroundId(selectedItemViewState.id)
    }
}