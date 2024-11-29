package com.tribalfs.stargazers.data.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                /*
                If you make more than 60 requests per hour, you will need an access token:
                https://docs.github.com/en/rest/using-the-rest-api/rate-limits-for-the-rest-api
                .addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                */
                .build()
            chain.proceed(request)
        }
        .build()

    @JvmStatic
    val instance: GitHubService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
    }
}
