package com.murilonerdx.apirestkotlin.model

data class Usuario(
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,
)
