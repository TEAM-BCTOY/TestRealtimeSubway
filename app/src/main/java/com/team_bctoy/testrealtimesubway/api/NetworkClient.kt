package com.team_bctoy.testrealtimesubway.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.team_bctoy.testrealtimesubway.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.internal.platform.Platform.Companion.INFO
import okhttp3.internal.platform.Platform.Companion.WARN
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    class PrettyJsonLogger : HttpLoggingInterceptor.Logger {
        private val gson = GsonBuilder().setPrettyPrinting().create()
        override fun log(message: String) {
            val trimMessage = message.trim()
            if ((trimMessage.startsWith("{") && trimMessage.endsWith("}"))
                || (trimMessage.startsWith("[") && trimMessage.endsWith("]"))) {
                try {
                    val prettyJson = gson.toJson(JsonParser().parse(message))
                    Platform.get().log(prettyJson, INFO,null)
                } catch (e: Exception) {
                    Platform.get().log(message, WARN, e)
                }
            } else {
                Platform.get().log(message, INFO, null)
            }
        }

    }

    private val interceptor = HttpLoggingInterceptor(PrettyJsonLogger()).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val apiClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.TEST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(apiClient)
        .build()

    fun getApiInstance() : NetworkInterface {
        return retrofit.create(NetworkInterface::class.java)
    }
}