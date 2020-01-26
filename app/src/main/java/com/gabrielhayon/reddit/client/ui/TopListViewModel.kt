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
    val postRead: MutableLiveData<Post> = MutableLiveData()

    private var after: String? = null
    //FIXME: Should be saved in DB
    private var lastPosts: ArrayList<Post> = arrayListOf()

    suspend fun getTopPost(context: Context, bringFromBeggining: Boolean) =
        withContext(Dispatchers.IO) {
            if (bringFromBeggining) cleanSavedPosts(context)

            val serviceResponse = RedditRepository.getTopPosts(15, after)
            if (serviceResponse.isOK()) {
                val redditResponse = serviceResponse.response as RedditResponse

                after = redditResponse.data.after

                val postsToShow =
                    getPostsToShow(context, redditResponse.data.children.map { it.data })
                lastPosts.addAll(postsToShow)

                withContext(Dispatchers.Main) {
                    posts.value = lastPosts
                }
            }

            //TODO: Show error view when serviceReponse is not successful
        }

    suspend fun markPostAsRead(context: Context, name: String) = withContext(Dispatchers.IO) {
        RedditRepository.saveReadPostsIds(context, listOf(name))

        withContext(Dispatchers.Main) {
            postRead.value = posts.value?.find { it.name == name }
        }
    }

    suspend fun markPostAsDismissed(context: Context, name: String) = withContext(Dispatchers.IO) {
        markPostsAsDismissed(context, listOf(name))
    }

    suspend fun markAllPostAsDismissed(context: Context) = withContext(Dispatchers.IO) {
        markPostsAsDismissed(context, posts.value!!.map { it.name })
    }

    suspend fun markPostsAsDismissed(context: Context, names: List<String>) =
        withContext(Dispatchers.IO) {
            RedditRepository.saveDismissedPostsIds(context, names)

            withContext(Dispatchers.Main) {
                posts.value = posts.value?.filterNot { names.contains(it.name) }
            }
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

    private fun cleanSavedPosts(context: Context) {
        after = null
        lastPosts = arrayListOf()
        RedditRepository.cleanDismissedPostsIds(context)
        RedditRepository.cleanReadPostsIds(context)
    }
}