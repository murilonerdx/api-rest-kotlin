package com.murilonerdx.apirestkotlin.service

import com.murilonerdx.apirestkotlin.model.Resposta
import com.murilonerdx.apirestkotlin.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {

    fun salvar(resposta: Resposta) {
        respostaRepository.save(resposta)
        val emailAutor = resposta.topico.autor.email
        emailService.notificar(emailAutor)
    }
}