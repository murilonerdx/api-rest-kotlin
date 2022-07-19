package com.murilonerdx.apirestkotlin.security

import com.murilonerdx.apirestkotlin.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
       return null
    }

    override fun getPassword(): String {
       return usuario.password
    }

    override fun getUsername(): String {
        return usuario.email
    }

    override fun isAccountNonExpired(): Boolean {
       return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}