package com.devwujot.tumblrsearch.framework.data

import androidx.lifecycle.MutableLiveData
import com.devwujot.tumblrsearch.core.data.Post
import com.devwujot.tumblrsearch.core.data.Resource
import com.devwujot.tumblrsearch.core.repository.TumblrDataSource
import com.devwujot.tumblrsearch.framework.data.remote.TumblrApiService
import com.devwujot.tumblrsearch.presentation.utility.*
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class RepositoryDataSource(val remoteSource: TumblrApiService) :  TumblrDataSource {

    override fun getPostsByUsername(username: String, postsResponse: MutableLiveData<Resource<*>>) {
        postsResponse.value = Resource.loading(true)
        remoteSource.getResponse(username)
            .enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    postsResponse.value = Resource.error(POSTS_REQUEST_ERROR_CALL_FAILURE)
                }
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val posts = handleResponse(response)
                        if (posts.size >= RESPONSE_LIST_SIZE_EQOUAL_OR_GRATER ) {
                            postsResponse.value = Resource.success(posts)
                        } else {
                            postsResponse.value = Resource.error("Username ${username} did not add any posts yet.")
                        }
                    } else if (response.code() == RESPONSE_CODE_NOT_FOUND) {
                        postsResponse.value = Resource.error("Username ${username} does not exists.")
                    }
                }
            })
    }

    private fun handleResponse(response: Response<String>) : ArrayList<Post> {
        val responseValue = response.body()
        val responseToJson = responseValue?.removeSurrounding(RESPONSE_REMOVE_PREFIX, RESPONSE_REMOVE_SUFFIX)
        val responseToJsonObject = JSONObject(responseToJson)
        val responseJsonObject = responseToJsonObject.getJSONArray(RESPONSE_POSTS_OBJECT_PLACEHOLDER)
        val posts = arrayListOf<Post>()
        for (i in 0 until responseJsonObject.length()) {
            val post = Gson().fromJson<Post>(
                responseJsonObject.get(i).toString(),
                Post::class.java
            )
            posts.add(post)
        }
        return posts
    }
}