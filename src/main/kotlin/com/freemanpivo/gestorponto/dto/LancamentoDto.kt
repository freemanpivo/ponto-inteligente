package com.freemanpivo.gestorponto.dto

import javax.validation.constraints.NotEmpty

data class LancamentoDto (
        @get:NotEmpty(message="Campo data de registro do ponto é obrigatório.")
        val dataRegistroPonto: String? = null,
        @get:NotEmpty(message="Campo tipo de lançamento é obrigatório.")
        val tipo: String? = null,

        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: String? = null,
        var id: String? = null
)