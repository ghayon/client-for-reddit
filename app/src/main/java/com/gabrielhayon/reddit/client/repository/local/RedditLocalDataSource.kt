package com.gabrielhayon.reddit.client.repository.local

import android.content.Context
import com.gabrielhayon.reddit.client.model.RedditResponse

interface RedditLocalDataSource {
    fun getTopPosts(): RedditResponse?

    fun saveTopPosts(redditResponse: RedditResponse)

    fun getReadPostsIds(context: Context): List<String>

    fun saveReadPostsIds(context: Context, readPostsIds: List<String>)

    fun cleanReadPostsIds(context: Context)

    fun getDismissedPostsIds(context: Context): List<String>

    fun saveDismissedPostsIds(context: Context, dismissedPostsIds: List<String>)

    fun cleanDismissedPostsIds(context: Context)
}