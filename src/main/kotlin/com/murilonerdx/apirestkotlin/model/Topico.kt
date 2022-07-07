package com.murilonerdx.apirestkotlin.model

import com.murilonerdx.apirestkotlin.model.enums.StatusTopico
import lombok.Data
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.jvm.Transient

@Data
@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @OneToOne  val curso: Curso,
    @OneToOne val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,

    @Transient
    val respostas: List<Resposta> = ArrayList()

) {

    override fun toString(): String {
        return "Topico(id=$id, titulo='$titulo', mensagem='$mensagem', dataCriacao=$dataCriacao, curso=$curso, autor=$autor, status=$status, respostas=$respostas)"
    }
}