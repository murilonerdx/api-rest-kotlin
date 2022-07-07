package com.murilonerdx.apirestkotlin.model

import lombok.Data
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Data
@Entity
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Usuario

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , nome = $nome , email = $email , password = $password )"
    }

}
