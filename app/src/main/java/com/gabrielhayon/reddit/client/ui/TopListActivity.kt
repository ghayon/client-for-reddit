package com.gabrielhayon.reddit.client.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gabrielhayon.reddit.client.R

class TopListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_list_activity)

        val vm = ViewModelProviders.of(this).get(TopListViewModel::class.java)

        Log.d("TopListActivity", if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) "true" else "false")
    }
}