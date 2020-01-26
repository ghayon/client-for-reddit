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

        if (isLandscape()) {
            addPostsListFragment(R.id.postsFragmentContainer)
            addPostDetailFragment(R.id.postDetailFragmentContainer)
        } else {
            addPostsListFragment(R.id.fragmentContainer)
        }
    }

    override fun onBackPressed() {
        if (!isLandscape() && postDetailFragment.isVisible) {
            hidePostDetailFragment()
            addPostsListFragment(R.id.fragmentContainer)
        } else {
            super.onBackPressed()
        }
    }

    private fun updateDetailFragment(post: Post) {
        if (!isLandscape()) {
            postDetailFragment.postToShow = post
            addPostDetailFragment(R.id.fragmentContainer)
            hidePostsListFragment()
        }
    }

    private fun hidePostDetailFragment() {
        val postDetailTransaction = supportFragmentManager.beginTransaction()
        postDetailTransaction.hide(postDetailFragment)
        postDetailTransaction.commit()
    }

    private fun hidePostsListFragment() {
        val postListTransaction = supportFragmentManager.beginTransaction()
        postListTransaction.hide(postListFragment)
        postListTransaction.commit()
    }

    private fun addPostsListFragment(layoutId: Int) {
        val postListTransaction = supportFragmentManager.beginTransaction()
        with(postListTransaction) {
            if (postListFragment.isHidden) show(postListFragment) else add(
                layoutId,
                postListFragment
            )
            commit()
        }
    }

    private fun addPostDetailFragment(layoutId: Int) {
        val postDetailTransaction = supportFragmentManager.beginTransaction()
        with(postDetailTransaction) {
            if (postDetailFragment.isHidden) show(postDetailFragment) else add(
                layoutId,
                postDetailFragment
            )
            commit()
        }
    }

    private fun isLandscape() = resources.configuration.orientation == ORIENTATION_LANDSCAPE
}