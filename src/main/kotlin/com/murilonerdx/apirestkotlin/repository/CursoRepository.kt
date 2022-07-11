package com.murilonerdx.apirestkotlin.repository

import com.murilonerdx.apirestkotlin.model.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository: JpaRepository<Curso, Long> {
}