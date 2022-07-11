package com.murilonerdx.apirestkotlin.repository

import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.model.Resposta
import com.murilonerdx.apirestkotlin.model.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(nome: String): List<Topico>

    @Query("select r from Resposta r where r.topico.autor.nome = :autor")
    fun findByTopicoAutorNome(autor: String): List<Resposta>
}