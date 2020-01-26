package com.gabrielhayon.reddit.client.ui.postslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.databinding.PostListItemBinding
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.ui.TopListViewModel

class PostListAdapter(private val activityVM: TopListViewModel) :
    RecyclerView.Adapter<PostListAdapter.PostItemViewHolder>() {
    var posts: List<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val view = DataBindingUtil.inflate<PostListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_list_item, parent, false
        )
        return PostItemViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class PostItemViewHolder(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.data = PostItemViewModel(post, activityVM)
        }
    }
}