package com.devwujot.tumblrsearch.framework.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devwujot.tumblrsearch.core.data.Post

class DetailActivityViewModel : ViewModel() {

    val post by lazy { MutableLiveData<Post>() }

    fun assignPost(post: Post) {
        this.post.value = post
    }
}