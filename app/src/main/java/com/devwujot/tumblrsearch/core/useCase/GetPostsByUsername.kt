package com.devwujot.tumblrsearch.core.useCase

import androidx.lifecycle.MutableLiveData
import com.devwujot.tumblrsearch.core.data.Resource
import com.devwujot.tumblrsearch.core.repository.TumblrRepository

class GetPostsByUsername(private val tumblrRepository: TumblrRepository) {

    operator fun invoke(username: String, postsResponse: MutableLiveData<Resource<*>>) =
        tumblrRepository.getPostsByUsername(username, postsResponse)
}