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
        @Query("apikey")apiKey: String = API_KEY,
        @Query("hash")hash: String = hash()

        ):CharactersList


    companion object{
        const val Base_URL = "https://gateway.marvel.com"
        val Time_Stamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = ""
        const val PRIVATE_KEY = ""
        fun hash():String{
            val input = "$Time_Stamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }


    }



}