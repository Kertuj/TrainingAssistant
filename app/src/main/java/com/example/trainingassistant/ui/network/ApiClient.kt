package com.example.trainingassistant.ui.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val TAG = "Repository"
    private val gson = Gson().newBuilder().serializeNulls().create() // important for synchronization purposes !!!
    fun create(): ApiInterface {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl("")
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface::class.java)
    }
}