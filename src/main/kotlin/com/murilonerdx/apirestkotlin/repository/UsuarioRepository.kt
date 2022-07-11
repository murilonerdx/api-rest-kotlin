package br.com.alura.forum.repository

import com.murilonerdx.apirestkotlin.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}