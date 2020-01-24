package com.gabrielhayon.reddit.client.repository.remote

import com.gabrielhayon.reddit.client.model.RedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {
    @GET("top.json")
    fun getTopPosts(@Query("limit") limit: String, @Query("after") after: String = ""):
            Call<RedditResponse>
}