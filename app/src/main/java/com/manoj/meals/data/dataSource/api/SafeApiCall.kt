package com.manoj.meals.data.dataSource.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {

    suspend fun <T> callApi(call: suspend () -> T): ApiDataWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiDataWrapper.Success(call.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ApiDataWrapper.Failure(throwable.code(), throwable.response()?.errorBody())
                    }

                    else -> {
                        ApiDataWrapper.Failure(null, null)
                    }
                }
            }
        }
    }
}