package com.murilonerdx.apirestkotlin.repository

import com.murilonerdx.apirestkotlin.model.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository : JpaRepository<Topico, Long> {
}