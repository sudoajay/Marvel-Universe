package com.oyelabs.marvel.universe.helper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PicsumApiInterface {

    /**
     * The return type is important here
     * The class structure that you've defined in Call<T>
     * should exactly match with your json response
     * If you are not using another api, and using the same as mine
     * then no need to worry, but if you have your own API, make sure
     * you change the return type appropriately
    </T> */


    @GET("list")
    suspend fun getPersonGsonPaging(@Query("page") page: Int, @Query("limit") size: Int): List<PersonGson>



    companion object{
        const val baseUrl = "https://picsum.photos/v2/"

    }



}