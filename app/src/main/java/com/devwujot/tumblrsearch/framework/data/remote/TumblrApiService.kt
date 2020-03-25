package com.devwujot.tumblrsearch.framework.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TumblrApiService {

    @GET("https://{username}.tumblr.com/api/read/json?callback=Tumbrl")
    fun getResponse(@Path("username") username: String): Call<String>
}