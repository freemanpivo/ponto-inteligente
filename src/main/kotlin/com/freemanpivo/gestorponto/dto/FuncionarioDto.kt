package com.freemanpivo.gestorponto.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class FuncionarioDto (
        @get:NotEmpty(message ="Nome não pode ser vazio")
        @get:Length(min = 3, max = 200, message = "Nome deve conter mais de 3 caracteres e menos de 200")
        val nome: String = "",

        @get:NotEmpty(message ="Email não pode ser vazio")
        @get:Length(min = 5, max = 200, message = "Email deve conter mais de 5 caracteres e menos de 200")
        @get:Email(message = "Email inválido")
        val email: String = "",

        val senha: String? = null,
        val valorHora: String? = null,
        val horasTrabalhadasNoDia: String? = null,
        val quantidadeHorasDeAlmoco: String? = null,
        val id: String? = null
)