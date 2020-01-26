package com.gabrielhayon.reddit.client.ui.postslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.ui.TopListViewModel
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : Fragment() {

    private val vm: TopListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.posts.observe(this, Observer { updatePosts(it) })

        postsList.layoutManager = LinearLayoutManager(activity)
        postsList.adapter = PostListAdapter(vm)
    }

    private fun updatePosts(posts: List<Post>) {
        val adapter = postsList.adapter as PostListAdapter
        adapter.posts = posts
        adapter.notifyDataSetChanged()
    }
}