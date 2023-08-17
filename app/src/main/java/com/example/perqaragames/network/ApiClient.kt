package com.example.perqaragames.network

import com.example.perqaragames.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        private const val TIMEOUT: Long = 30
        private var singleton: Retrofit? = null
        private var gson: Gson = GsonBuilder().setLenient().create()

        fun getRetrofit(): Retrofit {
            if (singleton == null) {
                synchronized(this) {
                    singleton = Retrofit.Builder()
                        .baseUrl(UrlConfig.HTTP)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getClient())
                        .build()
                }
            }
            return singleton!!
        }

        private fun getClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            return OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }
                .build()
        }
    }
}