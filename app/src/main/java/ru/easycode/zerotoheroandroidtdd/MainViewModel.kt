package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper : LiveDataWrapper,
    private val repository : Repository,
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        liveDataWrapper.update(Init)
    }

    fun liveData() = liveDataWrapper.liveData()

    fun load(){
        viewModelScope.launch {
            liveDataWrapper.update(Loading)
            repository.load()
            liveDataWrapper.update(Loaded)
        }
    }
}