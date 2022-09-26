package com.example.vamatest.baseClasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = viewModelScope.coroutineContext

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    protected val loadingLiveData = MutableLiveData<Boolean>()

    suspend fun <T> withProgress(
        progressLiveData: MutableLiveData<Boolean> = loadingLiveData,
        show: Boolean = true,
        block: suspend () -> T,
    ): T {
        if (show) progressLiveData.postValue(true)
        return try {
            block()
        } finally {
            if (show) progressLiveData.postValue(false)
        }
    }

    fun launchWithProgress(
        scope: CoroutineScope = this,
        progressLiveData: MutableLiveData<Boolean> = loadingLiveData,
        show: Boolean = true,
        block: suspend () -> Unit,
    ) = scope.launch { withProgress(progressLiveData, show, block) }
}