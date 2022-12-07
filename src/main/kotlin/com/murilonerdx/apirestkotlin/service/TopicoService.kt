package com.murilonerdx.apirestkotlin.service


import com.murilonerdx.apirestkotlin.dto.AtualizacaoTopicoForm
import com.murilonerdx.apirestkotlin.dto.NovoTopicoForm
import com.murilonerdx.apirestkotlin.dto.TopicoPorCategoriaDTO
import com.murilonerdx.apirestkotlin.dto.TopicoView
import com.murilonerdx.apirestkotlin.exception.NotFoundException
import com.murilonerdx.apirestkotlin.model.mapper.TopicoFormMapper
import com.murilonerdx.apirestkotlin.model.mapper.TopicoViewMapper
import com.murilonerdx.apirestkotlin.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico nao encontrado!"
) {

    @Cacheable(cacheNames = ["Topicos"], key = "#root.method.name")
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = nomeCurso?.let {
            repository.findByCursoNome(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)

        return topicos.map { t ->
            topicoViewMapper.mapper(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{ NotFoundException(notFoundMessage) }
        return topicoViewMapper.mapper(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.mapper(form)
        repository.save(topico)
        return topicoViewMapper.mapper(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.mapper(topico)
    }

    @CacheEvict(cacheNames = ["Topicos"], allEntries = true)
    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDTO> {
        return repository.relatorio()
    }
}