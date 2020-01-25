package com.gabrielhayon.reddit.client.repository

import android.content.Context
import com.gabrielhayon.reddit.client.repository.local.RedditLocalDataSource
import com.gabrielhayon.reddit.client.repository.local.SharedPreferenceRedditDataSource
import com.gabrielhayon.reddit.client.repository.remote.RedditRemoteDataSource
import com.gabrielhayon.reddit.client.repository.remote.RemoteRedditDataSource
import java.io.IOException

object RedditRepository {

    private val remoteRedditDataSource: RedditRemoteDataSource = RemoteRedditDataSource()
    private val localRedditDataSource: RedditLocalDataSource = SharedPreferenceRedditDataSource()

    fun getTopPosts(limit: Int, after: String?): ServiceResponse {
        return try {
            val response = remoteRedditDataSource.getTopPosts(limit, after)

            if (response.isSuccessful) ServiceResponse.buildSuccessful(response.body()!!) else ServiceResponse.buildServiceError()
        } catch (e: IOException) {
            ServiceResponse.buildNetworkError()
        } catch (e: Exception) {
            ServiceResponse.buildServiceError()
        }
    }

    fun getReadPostsIds(context: Context): List<String> {
        return localRedditDataSource.getReadPostsIds(context)
    }

    fun saveReadPostsIds(context: Context, readPostsIds: List<String>) {
        localRedditDataSource.saveReadPostsIds(context, readPostsIds)
    }

    fun getDismissedPostsIds(context: Context): List<String> {
        return localRedditDataSource.getDismissedPostsIds(context)
    }

    fun saveDismissedPostsIds(context: Context, dismissedPostsIds: List<String>) {
        localRedditDataSource.saveReadPostsIds(context, dismissedPostsIds)
    }
}