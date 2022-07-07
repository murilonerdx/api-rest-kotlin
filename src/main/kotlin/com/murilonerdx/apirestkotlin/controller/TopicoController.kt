package com.murilonerdx.apirestkotlin.controller

import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.model.Resposta
import com.murilonerdx.apirestkotlin.model.Topico
import com.murilonerdx.apirestkotlin.model.Usuario
import com.murilonerdx.apirestkotlin.model.enums.StatusTopico
import com.murilonerdx.apirestkotlin.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/topicos")
class TopicoController(private val repository: TopicoRepository) {

    @GetMapping
    fun listar(): List<Topico> =  repository.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable("id") id: Long): Topico = repository.findById(id).get()

    @PostMapping
    fun criarTopico(@RequestBody topico: Topico): Topico {
        val topicoCreate = Topico(
            id = null,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas
        )

        return repository.save(topicoCreate)
    }

    @PutMapping("/{id}")
    fun atualizarTopico(@PathVariable("id") id: Long, @RequestBody topico: Topico) : Topico{
        val topicoAtt = repository.findById(id).get()

        val topicoAtualizao = Topico(
            id = topicoAtt.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas
        )
        return repository.save(topicoAtualizao);
    }

    @DeleteMapping
    fun deletarTopico(@PathVariable("id") id: Long){
        val topico = repository.findById(id).get()
        repository.delete(topico)
    }
}