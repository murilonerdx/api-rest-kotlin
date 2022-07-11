package com.murilonerdx.apirestkotlin.controller

import com.murilonerdx.apirestkotlin.repository.TopicoRepository
import com.murilonerdx.apirestkotlin.dto.TopicoDTO
import com.murilonerdx.apirestkotlin.exception.NotFoundException
import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.model.Topico
import com.murilonerdx.apirestkotlin.model.Usuario
import com.murilonerdx.apirestkotlin.model.mapper.TopicoViewMapper
import com.murilonerdx.apirestkotlin.repository.CursoRepository
import com.murilonerdx.apirestkotlin.repository.UsuarioRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/topicos")
@CrossOrigin("*")
class TopicoController(
    private val repository: TopicoRepository,
    private val cursoRepository: CursoRepository,
    private val usuarioRepository: UsuarioRepository
) {

    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @PageableDefault(
            size = 5,
            sort = ["dataCriacao"],
            direction = Sort.Direction.DESC
        ) paginacao: Pageable
    ): List<TopicoDTO> = repository.findAll(paginacao).mapNotNull { x -> TopicoViewMapper().mapper(x) }.toList()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable("id") id: Long): ResponseEntity<TopicoDTO> = ResponseEntity.ok().body(
        TopicoViewMapper().mapper(repository.findById(id)
            .orElseThrow { NotFoundException("Id " + id + " não encontrado") })
    )

    @PostMapping
    @CacheEvict("topicos", allEntries = true)
    fun criarTopico(@RequestBody topico: TopicoDTO): ResponseEntity<TopicoDTO>? {
        if(topico.autor != null && topico.curso != null){
            val usuario: Usuario = this.usuarioRepository.findById(topico.autor).orElseThrow { NotFoundException("Id " + topico.autor + " não encontrado")  }
            val curso: Curso = this.cursoRepository.findById(topico.curso).orElseThrow { NotFoundException("Id " + topico.curso + " não encontrado")  }

            val topicoCreate = Topico(
                id = null,
                titulo = topico.titulo,
                mensagem = topico.mensagem,
                curso = curso,
                autor = usuario,
            )

            return ResponseEntity.ok().body(TopicoViewMapper().mapper(repository.save(topicoCreate)))
        }

        return null
    }

    @GetMapping("/curso")
    fun buscarNomCurso(
        @RequestParam("nomeCurso") nomeCurso: String,
        @PageableDefault(size = 5) pageable: Pageable
    ): ResponseEntity<List<TopicoDTO>> = ResponseEntity
        .ok()
        .body(repository.findByCursoNome(nomeCurso, pageable).map { x -> TopicoViewMapper().mapper(x) }.toList())

    @PutMapping("/{id}")
    @CacheEvict("topicos", allEntries = true)
    fun atualizarTopico(@PathVariable("id") id: Long, @RequestBody topico: TopicoDTO): ResponseEntity<TopicoDTO>? {
        val topicoAtt = repository.findById(id).get()

        if(topico.autor != null && topico.curso != null){
            val usuario: Usuario = this.usuarioRepository.findById(topico.autor).orElseThrow { NotFoundException("Id " + topico.autor + " não encontrado")  }
            val curso: Curso = this.cursoRepository.findById(topico.curso).orElseThrow { NotFoundException("Id " + topico.curso + " não encontrado")  }

            val topicoAtualizao = Topico(
                id = topicoAtt.id,
                titulo = topico.titulo,
                mensagem = topico.mensagem,
                curso = curso,
                autor = usuario,
            )
            return ResponseEntity.ok().body(TopicoViewMapper().mapper(repository.save(topicoAtualizao)))
        }
       return null
    }

    @DeleteMapping("/{id}")
    @CacheEvict("topicos", allEntries = true)
    fun deletarTopico(@PathVariable("id") id: Long) {
        val topico = repository.findById(id).get()
        repository.delete(topico)
    }
}