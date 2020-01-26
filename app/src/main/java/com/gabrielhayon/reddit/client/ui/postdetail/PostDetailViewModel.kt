package com.gabrielhayon.reddit.client.ui.postdetail

import com.gabrielhayon.reddit.client.model.Post

class PostDetailViewModel(private val post: Post) {
    fun getTitle(): String? {
        return post.title
    }

    fun getUsername(): String {
        return post.author
    }

    fun getThumbnailUrl(): String? {
        return post.thumbnail
    }
}