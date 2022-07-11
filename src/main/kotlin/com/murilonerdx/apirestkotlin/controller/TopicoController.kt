package com.murilonerdx.apirestkotlin.controller

import com.murilonerdx.apirestkotlin.dto.TopicoDTO
import com.murilonerdx.apirestkotlin.exception.NotFoundException
import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.model.Resposta
import com.murilonerdx.apirestkotlin.model.Topico
import com.murilonerdx.apirestkotlin.model.Usuario
import com.murilonerdx.apirestkotlin.model.enums.StatusTopico
import com.murilonerdx.apirestkotlin.model.mapper.TopicoViewMapper
import com.murilonerdx.apirestkotlin.repository.TopicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/topicos")
@CrossOrigin("*")
class TopicoController(private val repository: TopicoRepository) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(@PageableDefault(size=5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable): List<TopicoDTO> = repository.findAll(paginacao).mapNotNull { x -> TopicoViewMapper().mapper(x) }.toList()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable("id") id: Long): ResponseEntity<TopicoDTO> =  ResponseEntity.ok().body(TopicoViewMapper().mapper(repository.findById(id)
        .orElseThrow { NotFoundException("Id " + id + " não encontrado") }))

    @PostMapping
    @CacheEvict("topicos", allEntries = true)
    fun criarTopico(@RequestBody topico: Topico): ResponseEntity<TopicoDTO> {
        val topicoCreate = Topico(
            id = null,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas
        )

        return ResponseEntity.ok().body(TopicoViewMapper().mapper(repository.save(topicoCreate)))
    }

    @GetMapping("/curso")
    fun buscarNomCurso(@RequestParam("nomeCurso") nomeCurso: String): ResponseEntity<List<TopicoDTO>> =  ResponseEntity
        .ok()
        .body(repository.findByCursoNome(nomeCurso).map { x -> TopicoViewMapper().mapper(x) }.toList())

    @PutMapping("/{id}")
    @CacheEvict("topicos", allEntries = true)
    fun atualizarTopico(@PathVariable("id") id: Long, @RequestBody topico: Topico) :ResponseEntity<TopicoDTO>{
        val topicoAtt = repository.findById(id).get()

        val topicoAtualizao = Topico(
            id = topicoAtt.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas
        )
        return ResponseEntity.ok().body(TopicoViewMapper().mapper(repository.save(topicoAtualizao)))
    }

    @DeleteMapping
    @CacheEvict("topicos", allEntries = true)
    fun deletarTopico(@PathVariable("id") id: Long){
        val topico = repository.findById(id).get()
        repository.delete(topico)
    }
}