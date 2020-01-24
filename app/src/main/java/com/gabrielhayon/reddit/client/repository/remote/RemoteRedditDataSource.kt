package com.gabrielhayon.reddit.client.repository.remote

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gabrielhayon.reddit.client.model.RedditResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

//TODO: Change name to a better one
class RemoteRedditDataSource :
    RedditRemoteDataSource {
    private val objectMapper: ObjectMapper
    private var retrofit: Retrofit
    private var redditService: RedditService

    init {
        objectMapper = jacksonObjectMapper().apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        }
        retrofit = initRetrofit()
        redditService = retrofit.create(RedditService::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper)).build()
    }

    private fun getOkHttpClient() = OkHttpClient.Builder().apply {
        connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
    }.build()

    override fun getTopPosts(limit: String, after: String?): Response<RedditResponse> {
        return redditService.getTopPosts(limit, after ?: "").execute()
    }

    private companion object {
        const val NETWORK_TIMEOUT: Long = 10
        const val BASE_URL = "https://www.reddit.com/"
    }
}