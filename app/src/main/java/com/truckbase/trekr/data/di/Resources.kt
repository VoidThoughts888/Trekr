package com.truckbase.trekr.data.di

import okhttp3.ResponseBody

sealed class Resources<out T> {
    data class Success<out T>(val data : String): Resources<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode : Int?,
        val errorResponse : ResponseBody?
    ): Resources<Nothing>()
    object Loading : Resources<Nothing>()
}