package br.com.alura.forum.model

import com.murilonerdx.apirestkotlin.model.Usuario

object UsuarioTest {
    fun build() = Usuario(id = 1, nome = "Joao", email = "jvc.martins", password = "123")
    fun buildToToken() = Usuario(nome = "Ana da Silva", email = "ana@email.com", password = "123456")
}