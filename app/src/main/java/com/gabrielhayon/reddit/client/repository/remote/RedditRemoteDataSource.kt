package com.gabrielhayon.reddit.client.repository.remote

import com.gabrielhayon.reddit.client.model.RedditResponse
import retrofit2.Response

interface RedditRemoteDataSource {
    fun getTopPosts(limit: Int, after: String?): Response<RedditResponse>
}