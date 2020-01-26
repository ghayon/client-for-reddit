package com.gabrielhayon.reddit.client.repository.local

import android.content.Context
import com.gabrielhayon.reddit.client.model.RedditResponse

class SharedPreferenceRedditDataSource() : RedditLocalDataSource {
    override fun getReadPostsIds(context: Context): List<String> {
        return getSharedPreferences(context).getStringSet(READ_POSTS_KEY, mutableSetOf())!!.toList()
    }

    override fun saveReadPostsIds(context: Context, readPostsIds: List<String>) {
        val postIdsToSave: ArrayList<String> = arrayListOf()
        getReadPostsIds(context).toCollection(postIdsToSave)
        readPostsIds.toCollection(postIdsToSave)

        getSharedPreferences(context).edit().apply {
            putStringSet(READ_POSTS_KEY, postIdsToSave.toMutableSet())
            apply()
        }
    }

    override fun getDismissedPostsIds(context: Context): List<String> {
        return getSharedPreferences(context).getStringSet(
            DISMISSED_POSTS_KEY,
            mutableSetOf()
        )!!.toList()
    }

    override fun saveDismissedPostsIds(context: Context, dismissedPostsIds: List<String>) {
        val postIdsToSave: ArrayList<String> = arrayListOf()
        getDismissedPostsIds(context).toCollection(postIdsToSave)
        dismissedPostsIds.toCollection(postIdsToSave)

        getSharedPreferences(context).edit().apply {
            putStringSet(DISMISSED_POSTS_KEY, postIdsToSave.toMutableSet())
            apply()
        }
    }

    override fun getTopPosts(): RedditResponse? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTopPosts(redditResponse: RedditResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getSharedPreferences(context: Context) =
        context.getSharedPreferences(REDDIT_POSTS_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    private companion object {
        const val REDDIT_POSTS_SHARED_PREFERENCES = "REDDIT_POSTS_SHARED_PREFERENCES"
        const val READ_POSTS_KEY = "READ_POSTS_KEY"
        const val DISMISSED_POSTS_KEY = "DISMISSED_POSTS_KEY"
    }

}