package com.devwujot.tumblrsearch.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devwujot.tumblrsearch.core.data.Resource
import com.devwujot.tumblrsearch.framework.useCases.UseCases

class SearchActivityViewModel(val useCases: UseCases) : ViewModel() {

    private val _postsResponse by lazy { MutableLiveData<Resource<*>>() }
    val postsResponse: LiveData<Resource<*>>
        get() = _postsResponse

    fun getPostsByUsername(username: String) {
        useCases.getPostsByUsername(username, _postsResponse)
    }
}