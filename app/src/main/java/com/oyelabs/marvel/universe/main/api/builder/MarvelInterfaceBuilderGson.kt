package com.oyelabs.marvel.universe.main.api.builder

import android.util.Log
import com.oyelabs.marvel.universe.main.api.MarvelApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MarvelInterfaceBuilderGson {
    companion object {
        var marvelApiInterface: MarvelApiInterface? = null
        private var okHttpClient: OkHttpClient? = null
        var TAG = "MarvelInterfaceBuilderGsonTAG"

        fun getApiInterface(): MarvelApiInterface? {
            if (marvelApiInterface == null) {

                val retrofit = Retrofit.Builder()
                    .baseUrl(MarvelApiInterface.Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                marvelApiInterface = retrofit.create(MarvelApiInterface::class.java)
                Log.e(TAG , "Here at Not null")
            }
                Log.e(TAG , "Here at TAg")
            return marvelApiInterface
        }

    }

}