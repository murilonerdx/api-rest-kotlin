package com.murilonerdx.apirestkotlin.model.mapper

import com.murilonerdx.apirestkotlin.dto.TopicoDTO
import com.murilonerdx.apirestkotlin.dto.TopicoView
import com.murilonerdx.apirestkotlin.model.Topico
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {

    override fun mapper(t: Topico): TopicoView {
        return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status,
            dataAlteracao = t.dataAlteracao
        )
    }
}