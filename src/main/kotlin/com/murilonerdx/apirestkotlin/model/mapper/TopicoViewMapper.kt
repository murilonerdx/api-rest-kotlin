package com.murilonerdx.apirestkotlin.model.mapper

import com.murilonerdx.apirestkotlin.dto.TopicoDTO
import com.murilonerdx.apirestkotlin.model.Topico
import java.time.LocalDateTime

class TopicoViewMapper() : Mapper<Topico, TopicoDTO> {

    override fun mapper(t: Topico): TopicoDTO {
        return TopicoDTO(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = LocalDateTime.now(),
            curso = t.curso.id,
            autor = t.autor.id,
            status = t.status.ordinal
        )
    }



}