package com.oyelabs.marvel.universe.api

import com.oyelabs.marvel.universe.api.pojo.character.CharactersList
import com.oyelabs.marvel.universe.api.pojo.characterComic.CharacterComic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


interface MarvelApiInterface {


    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("ts")ts:String = Time_Stamp,
        @Query("apikey")apiKey: String = PUBLIC_KEY,
        @Query("hash")hash: String = hash(),
        @Query("offset")offset:Int
        ): CharactersList

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getCharactersComics(
        @Path(
            value = "characterId",
            encoded = true
        ) characterId: Int,
        @Query("ts")ts:String = Time_Stamp,
        @Query("apikey")apiKey: String = PUBLIC_KEY,
        @Query("hash")hash: String = hash(),
        @Query("offset")offset:Int
    ): CharacterComic

    companion object{
        const val Base_URL = "https://gateway.marvel.com"
        val Time_Stamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val PUBLIC_KEY = "42f0419e94fca6fd37e8175707d17466"
        const val PRIVATE_KEY = "4765a2ddf79679809efdff68baf6763154fb7275"

        const val NETWORK_PAGE_SIZE = 10
        const val STARTING_PAGE_INDEX = 0
        fun hash():String{
            val input = "$Time_Stamp$PRIVATE_KEY$PUBLIC_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }


    }



}