package com.example.vamatest.baseClasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = viewModelScope.coroutineContext +
        CoroutineExceptionHandler { _, throwable ->
            handleUnhandledError(throwable)
        }

    protected open fun handleUnhandledError(throwable: Throwable) {
        launchWithProgress {
            errorLiveData.postValue(throwable)
        }
    }

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    val error: LiveData<Throwable>
        get() = errorLiveData

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Throwable>()

    fun cleanError() {
        errorLiveData.postValue(null)
    }

    suspend fun <T> withProgress(
        progressLiveData: MutableLiveData<Boolean> = loadingLiveData,
        show: Boolean = true,
        block: suspend () -> T
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
        block: suspend () -> Unit
    ) = scope.launch { withProgress(progressLiveData, show, block) }
}
