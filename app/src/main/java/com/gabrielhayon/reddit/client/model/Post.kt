package com.gabrielhayon.reddit.client.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class Post(
    val title: String,
    val thumbnail: String?,
    val url: String,
    val createdUtc: Long,
    val name: String,
    val author: String,
    val numComments: String,
    @JsonIgnore
    var read: Boolean = false
) : Serializable