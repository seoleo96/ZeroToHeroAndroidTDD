package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper {
    fun update(value: UiState)
    fun liveData(): LiveData<UiState>
}

class LiveDataWrapperImpl : LiveDataWrapper{

    private val liveData = MutableLiveData<UiState>()

    override fun update(value: UiState) {
        liveData.value = value
    }

    override fun liveData(): LiveData<UiState> {
        return liveData
    }

}
