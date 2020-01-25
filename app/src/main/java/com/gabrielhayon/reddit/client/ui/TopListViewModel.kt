package com.gabrielhayon.reddit.client.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.model.RedditResponse
import com.gabrielhayon.reddit.client.repository.RedditRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopListViewModel : ViewModel() {

    val posts: MutableLiveData<List<Post>> = MutableLiveData()

    private var after: String? = null

    suspend fun getTopPost(context: Context) = withContext(Dispatchers.IO) {
        val serviceResponse = RedditRepository.getTopPosts(15, after)
        if (serviceResponse.isOK()) {
            val redditResponse = serviceResponse.response as RedditResponse

            after = redditResponse.data.after
            val postsToShow = getPostsToShow(context, redditResponse.data.children)
            withContext(Dispatchers.Main) {
                posts.value = postsToShow
            }
        }

        //TODO: Show error view when serviceReponse is not successful
    }

    fun markPostAsRead(context: Context, name: String) {
        RedditRepository.saveReadPostsIds(context, listOf(name))
    }

    fun markPostAsDismissed(context: Context, name: String) {
        markPostsAsDismissed(context, listOf(name))
    }

    fun markPostsAsDismissed(context: Context, names: List<String>) {
        RedditRepository.saveDismissedPostsIds(context, names)
    }

    private fun getPostsToShow(context: Context, posts: List<Post>): List<Post> {
        val notDismissedPosts = filterDismissedPosts(context, posts)
        return updatePostsReadStatus(context, notDismissedPosts)
    }

    private fun filterDismissedPosts(context: Context, posts: List<Post>): List<Post> {
        val names = RedditRepository.getDismissedPostsIds(context)
        return posts.filterNot { names.contains(it.name) }
    }

    private fun updatePostsReadStatus(context: Context, posts: List<Post>): List<Post> {
        val names = RedditRepository.getReadPostsIds(context)
        posts.forEach { it.read = names.contains(it.name) }
        return posts
    }
}