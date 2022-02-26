package com.example.ktorandroidapp.util

sealed class Ressource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?): Ressource<T>(data)
    class Error<T>(message: String, data: T? = null): Ressource<T>(data,message)
}
