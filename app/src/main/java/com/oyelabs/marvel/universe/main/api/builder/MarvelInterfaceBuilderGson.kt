package com.oyelabs.marvel.universe.main.api.builder

import com.oyelabs.marvel.universe.main.api.MarvelApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelInterfaceBuilderGson {
    companion object {
        var picsumApiInterface: MarvelApiInterface? = null
        private var okHttpClient: OkHttpClient? = null


        fun getApiInterface(): MarvelApiInterface? {
            if (picsumApiInterface == null) {
                //For printing API url and body in logcat
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
                picsumApiInterface = retrofit.create(MarvelApiInterface::class.java)
            }

            return picsumApiInterface
        }

    }

}