package com.gabrielhayon.reddit.client.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.gabrielhayon.reddit.client.R
import com.gabrielhayon.reddit.client.databinding.PostDetailFragmentBinding
import com.gabrielhayon.reddit.client.model.Post
import com.gabrielhayon.reddit.client.ui.TopListActivity
import com.gabrielhayon.reddit.client.ui.TopListViewModel

class PostDetailFragment : Fragment() {
    lateinit var binding: PostDetailFragmentBinding

    private val vm: TopListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.postRead.observe(activity as TopListActivity, Observer { showPost(it) })
    }

    fun showPost(post: Post) {
        binding.data = PostDetailViewModel(post)
    }
}