package com.gabrielhayon.reddit.client.ui.postslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.model.Post
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : Fragment() {

    val postsToShow: MutableLiveData<List<Post>> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsList.layoutManager = LinearLayoutManager(activity)

        postsToShow.observe(this, Observer { updatePosts(it) })
    }

    private fun updatePosts(posts: List<Post>) {
        postsList.adapter = PostListAdapter(posts)
        postsList.adapter!!.notifyDataSetChanged()
    }
}