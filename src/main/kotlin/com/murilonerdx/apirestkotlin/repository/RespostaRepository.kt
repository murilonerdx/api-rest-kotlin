package com.murilonerdx.apirestkotlin.repository

import com.murilonerdx.apirestkotlin.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long>