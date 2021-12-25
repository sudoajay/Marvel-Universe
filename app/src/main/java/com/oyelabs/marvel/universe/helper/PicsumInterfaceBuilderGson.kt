package com.oyelabs.marvel.universe.helper

import com.oyelabs.marvel.universe.helper.PicsumApiInterface.Companion.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PicsumInterfaceBuilderGson {
    companion object {
        var picsumApiInterface: PicsumApiInterface? = null
        private var okHttpClient: OkHttpClient? = null


        fun getApiInterface(): PicsumApiInterface? {
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
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient!!)
                    .build()
                picsumApiInterface = retrofit.create(PicsumApiInterface::class.java)
            }

            return picsumApiInterface
        }

    }

}