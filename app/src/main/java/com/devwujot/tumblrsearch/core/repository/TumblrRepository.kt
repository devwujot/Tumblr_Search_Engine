package com.devwujot.tumblrsearch.core.repository

import androidx.lifecycle.MutableLiveData
import com.devwujot.tumblrsearch.core.data.Resource

class TumblrRepository(val dataSource: TumblrDataSource) {

    fun getPostsByUsername(username: String, postsResponse: MutableLiveData<Resource<*>>) =
        dataSource.getPostsByUsername(username, postsResponse)
}