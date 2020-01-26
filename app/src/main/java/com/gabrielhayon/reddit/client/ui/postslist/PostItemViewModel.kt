package com.gabrielhayon.reddit.client.ui.postslist

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.gabrielhayon.reddit.client.BR
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.ui.TopListViewModel
import com.gabrielhayon.reddit.client.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class PostItemViewModel(private val post: Post, private val activityVM: TopListViewModel) :
    BaseObservable() {

    @Bindable
    fun getTextColor(): Int {
        return if (post.read) R.color.readTextColor else R.color.unreadTextColor
    }

    fun getTitle(): String? {
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

    fun getPostReadOnClikListener(): View.OnClickListener = View.OnClickListener {
        GlobalScope.launch(Dispatchers.IO) { activityVM.markPostAsRead(it.context, post.name) }
        post.read = true
        notifyPropertyChanged(BR.textColor)
    }

    fun getPostDismissOnClickListener(): View.OnClickListener = View.OnClickListener {
        GlobalScope.launch(Dispatchers.IO) { activityVM.markPostAsDismissed(it.context, post.name) }
    }
}