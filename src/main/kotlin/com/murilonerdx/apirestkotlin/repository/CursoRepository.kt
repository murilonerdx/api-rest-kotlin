package br.com.alura.forum.repository

import com.murilonerdx.apirestkotlin.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}