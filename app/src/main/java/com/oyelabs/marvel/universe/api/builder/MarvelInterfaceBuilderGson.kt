package com.oyelabs.marvel.universe.api.builder

import android.util.Log
import com.oyelabs.marvel.universe.api.MarvelApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelInterfaceBuilderGson {
    companion object {
        var marvelApiInterface: MarvelApiInterface? = null
        private var okHttpClient: OkHttpClient? = null

        fun getApiInterface(): MarvelApiInterface? {
            if (marvelApiInterface == null) {

                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .callTimeout(50, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(MarvelApiInterface.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient!!)
                    .build()
                marvelApiInterface = retrofit.create(MarvelApiInterface::class.java)

            }
            return marvelApiInterface
        }

    }

}