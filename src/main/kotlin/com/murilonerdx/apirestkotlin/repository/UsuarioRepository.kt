package com.murilonerdx.apirestkotlin.repository

import com.murilonerdx.apirestkotlin.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository: JpaRepository<Usuario, Long> {
}