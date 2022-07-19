package com.murilonerdx.apirestkotlin.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(p0: HttpServletRequest, p1: HttpServletResponse, p2: FilterChain) {
        val token = p0.getHeader("Authorization")
        val jwt = getTokenDetails(token)
        if(jwtUtil.isValid(jwt)){
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        p2.doFilter(p0, p1)
    }


    private fun getTokenDetails(token: String?): String?{
        return token?.let{jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }

}
