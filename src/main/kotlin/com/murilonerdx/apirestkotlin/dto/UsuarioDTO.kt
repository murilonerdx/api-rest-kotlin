package com.murilonerdx.apirestkotlin.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UsuarioDTO (
    @field:NotEmpty val nome: String,
    @field:Email(message="Digite um e-mail valido") val email: String,
    @field:Size(min=8) val password: String,
)