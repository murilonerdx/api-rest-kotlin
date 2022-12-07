package com.murilonerdx.apirestkotlin.model.mapper

import com.murilonerdx.apirestkotlin.dto.NovoTopicoForm
import com.murilonerdx.apirestkotlin.model.Topico
import com.murilonerdx.apirestkotlin.service.CursoService
import com.murilonerdx.apirestkotlin.service.UsuarioService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
): Mapper<NovoTopicoForm, Topico> {
    override fun mapper(t: NovoTopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor),
            dataAlteracao = LocalDate.now()
        )
    }
}