package com.oyelabs.marvel.universe.main.api

sealed class Response<T>(val data:T?=null,val message :String?=null){
    class Loading<T>(data: T?=null):Response<T>(data)
    class Success<T>(data: T):Response<T>(data)
    class Error<T>(data: T?=null, message: String):Response<T>(data, message)

}