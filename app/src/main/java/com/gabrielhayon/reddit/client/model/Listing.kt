package com.gabrielhayon.reddit.client.model

import java.io.Serializable

data class Listing(val children: List<Post>, val after: String) : Serializable