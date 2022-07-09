package com.murilonerdx.apirestkotlin.dto

import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.model.Usuario
import com.murilonerdx.apirestkotlin.model.enums.StatusTopico
import java.time.LocalDateTime
import javax.persistence.OneToOne

data class TopicoDTO(
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Long?,
    val autor: Long?,
    val status: Int
)