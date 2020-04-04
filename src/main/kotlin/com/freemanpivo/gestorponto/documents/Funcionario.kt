package com.freemanpivo.gestorponto.documents

import com.freemanpivo.gestorponto.enums.PerfilEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Funcionario (
        val nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        val empresaId: String,
        var valorHora: Double? = 0.0,
        var horasTrabalhadasNoDia: Float? = 0.0f,
        var quantidadeHorasAlmoco: Float? = 0.0f,
        @Id var id: String? = null
) {
    override fun toString(): String {
        return nome + email + senha + cpf + perfil + empresaId + valorHora + horasTrabalhadasNoDia +
                quantidadeHorasAlmoco + id
    }
}