package br.com.alura.forum.model

import com.murilonerdx.apirestkotlin.dto.TopicoView
import com.murilonerdx.apirestkotlin.model.enums.StatusTopico
import java.time.LocalDate
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        status = StatusTopico.NAO_RESPONDIDO,
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDate.now()
    )
}