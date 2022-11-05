package com.example.mmoapplication.data.core

data class State<out T>(
    val Status: Status,
    val data: T?,
    val error: Throwable?,
    val loading: Boolean
) {
    companion object {
        fun <T> onSuccess(data: T?): State<T> =
            State(Status.SUCCESS, data, null, false)

        fun <T> onError(error: Throwable?): State<T> =
            State(Status.ERROR, null, error, false)

        fun <T> onLoading(loading: Boolean): State<T> =
            State(Status.LOADING, null, null, loading)
    }
}