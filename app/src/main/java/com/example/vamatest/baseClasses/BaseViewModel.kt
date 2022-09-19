package com.example.vamatest.baseClasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = viewModelScope.coroutineContext
}