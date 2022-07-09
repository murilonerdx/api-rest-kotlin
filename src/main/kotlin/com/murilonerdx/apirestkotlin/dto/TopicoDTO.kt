package com.murilonerdx.apirestkotlin.dto

import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

data class TopicoDTO(
    val id: Long? = null,
    @Min(5) @NotEmpty(message="O titulo não pode estar vazia") val titulo: String,
    @NotEmpty(message="A descrição não pode estar vazia") val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Long?,
    val autor: Long?,
    val status: Int
)