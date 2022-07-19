package com.murilonerdx.apirestkotlin.service

import com.murilonerdx.apirestkotlin.repository.UsuarioRepository
import com.murilonerdx.apirestkotlin.security.UserDetail
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = username?.let { repository.findByEmail(it) } ?: throw RuntimeException()
        return UserDetail(usuario)
    }
}