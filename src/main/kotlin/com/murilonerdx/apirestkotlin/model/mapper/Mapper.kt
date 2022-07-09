package com.murilonerdx.apirestkotlin.model.mapper

interface Mapper<T, U> {
    fun mapper(t: T): U
}