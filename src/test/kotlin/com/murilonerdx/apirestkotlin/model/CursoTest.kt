package br.com.alura.forum.model

import com.murilonerdx.apirestkotlin.model.Curso

object CursoTest {
    fun build() = Curso(id = 1, nome = "Kotlin Basico", categoria = "Programacao")
}