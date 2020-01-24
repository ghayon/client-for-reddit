package com.gabrielhayon.reddit.client.model

import java.io.Serializable

data class Post(
    val title: String,
    val thumbnail: String,
    val url: String,
    val createdUtc: Long,
    val name: String,
    val author: String,
    val numComments: String
) : Serializable