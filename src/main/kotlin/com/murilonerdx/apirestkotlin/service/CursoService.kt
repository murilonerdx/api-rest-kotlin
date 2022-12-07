package com.murilonerdx.apirestkotlin.service

import com.murilonerdx.apirestkotlin.model.Curso
import com.murilonerdx.apirestkotlin.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id)
    }


}