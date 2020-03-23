package com.freemanpivo.gestorponto.response

data class Response<T> (
        val errors: ArrayList<String> = arrayListOf(),
        var body: T? = null
)