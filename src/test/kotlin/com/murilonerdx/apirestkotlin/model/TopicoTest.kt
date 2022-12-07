package br.com.alura.forum.model

import com.murilonerdx.apirestkotlin.model.Topico

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}