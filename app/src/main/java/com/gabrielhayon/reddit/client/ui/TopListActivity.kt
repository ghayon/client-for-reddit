package com.gabrielhayon.reddit.client.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.ui.postdetail.PostDetailFragment
import com.gabrielhayon.reddit.client.ui.postslist.PostListFragment
import kotlinx.android.synthetic.main.top_list_activity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopListActivity : AppCompatActivity() {
    private lateinit var vm: TopListViewModel

    private val postListFragment = PostListFragment()
    private val postDetailFragment = PostDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_list_activity)

        vm = ViewModelProviders.of(this).get(TopListViewModel::class.java)
        vm.postRead.observe(this, Observer { updateDetailFragment(it) })

        GlobalScope.launch { vm.getTopPost(this@TopListActivity, false) }

        swipeRefreshLayout.setOnRefreshListener {
            GlobalScope.launch { vm.getTopPost(this@TopListActivity, true) }
        }

        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            addPostsListFragment()
            addPostDetailFragment()
        } else {
            addPostsListFragment()
        }
    }

    private fun updateDetailFragment(post: Post) {
        if (resources.configuration.orientation != ORIENTATION_LANDSCAPE) {
            postDetailFragment.showPost(post)
        }
    }

    private fun addPostsListFragment() {
        val postListTransaction = supportFragmentManager.beginTransaction()
        postListTransaction.add(R.id.postsFragmentContainer, postListFragment)
        postListTransaction.commit()
    }

    private fun addPostDetailFragment() {
        val postDetailTransaction = supportFragmentManager.beginTransaction()
        postDetailTransaction.add(R.id.postDetailFragmentContainer, postDetailFragment)
        postDetailTransaction.commit()
    }
}