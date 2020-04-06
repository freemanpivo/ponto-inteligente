package com.freemanpivo.gestorponto.documents

import com.freemanpivo.gestorponto.enums.TipoEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document
class Lancamento (
        val data: Date,
        val tipo: TipoEnum,
        val descricao: String? = "",
        val localizacao: String? = "",
        val funcionarioId: String,
        @Id var id: String? = null
)