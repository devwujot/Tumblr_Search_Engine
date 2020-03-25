package com.devwujot.tumblrsearch.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devwujot.tumblrsearch.presentation.utility.SingleLiveEvent

class SplashScreenActivityViewModel : ViewModel() {

    private val _goToSearchViewObservable by lazy { SingleLiveEvent<Boolean>() }
    val goToSearchViewObservable: LiveData<Boolean>
        get() = _goToSearchViewObservable

    fun goToSearchView() {
        _goToSearchViewObservable.value = true
    }
}