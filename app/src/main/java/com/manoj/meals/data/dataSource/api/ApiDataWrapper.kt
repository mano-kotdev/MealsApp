package com.manoj.meals.data.dataSource.api

import okhttp3.ResponseBody

sealed class ApiDataWrapper<out T> {

    class Success<out T>(val value: T) : ApiDataWrapper<T>()

    class Failure(val errorCode: Int?, val errorResponse: ResponseBody?) : ApiDataWrapper<Nothing>()

    data object Loading : ApiDataWrapper<Nothing>()

    data object None : ApiDataWrapper<Nothing>()
}