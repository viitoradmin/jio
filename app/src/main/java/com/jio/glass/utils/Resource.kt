package com.jio.glass.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> emailError(): Resource<T> {
            return Resource(Status.EMAIL_ERROR, null, null)
        }

        fun <T> passwordError(): Resource<T> {
            return Resource(Status.PASSWORD_ERROR, null, null)
        }

    }

}

