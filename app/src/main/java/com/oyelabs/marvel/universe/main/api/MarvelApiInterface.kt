package com.oyelabs.marvel.universe.main.api

import com.oyelabs.marvel.universe.main.api.source.dto.CharactersList
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query
import java.math.BigInteger
import java.security.Key
import java.security.MessageDigest
import java.sql.Time
import java.sql.Timestamp


interface MarvelApiInterface {


    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("ts")ts:String = Time_Stamp,
        @Query("apikey")apiKey: String = PUBLIC_KEY,
        @Query("hash")hash: String = hash(),
        @Query("offset")offset:Int
        ):CharactersList


    companion object{
        const val Base_URL = "https://gateway.marvel.com"
        val Time_Stamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val PUBLIC_KEY = "42f0419e94fca6fd37e8175707d17466"
        const val PRIVATE_KEY = "4765a2ddf79679809efdff68baf6763154fb7275"

        const val NETWORK_PAGE_SIZE = 10
        const val STARTING_PAGE_INDEX = 1
        fun hash():String{
            val input = "$Time_Stamp$PRIVATE_KEY$PUBLIC_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }


    }



}