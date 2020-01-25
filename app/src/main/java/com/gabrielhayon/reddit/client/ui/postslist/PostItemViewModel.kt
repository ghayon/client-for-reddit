package com.gabrielhayon.reddit.client.ui.postslist

import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.utils.DateUtils
import java.util.*

class PostItemViewModel(private val post: Post) {
    fun getTextColor(): Int {
        return if (post.read) R.color.readTextColor else R.color.unreadTextColor
    }

    fun getTitle(): String {
        return post.title
    }

    fun getUsername(): String {
        return post.author
    }

    fun getPostHoursTimestamp(): String {
        val timestampDate = DateUtils.getTimestampDate(post.createdUtc)
        return DateUtils.getDifferenceInHours(timestampDate, Date()).toString()
    }

    fun getTotalComments(): String {
        return post.numComments
    }

    fun getThumbnailUrl(): String? {
        return post.thumbnail
    }
}