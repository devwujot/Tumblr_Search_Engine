package com.devwujot.tumblrsearch.core.repository

import androidx.lifecycle.MutableLiveData
import com.devwujot.tumblrsearch.core.data.Resource

interface TumblrDataSource {

    fun getPostsByUsername(username: String, postsResponse: MutableLiveData<Resource<*>>)
}