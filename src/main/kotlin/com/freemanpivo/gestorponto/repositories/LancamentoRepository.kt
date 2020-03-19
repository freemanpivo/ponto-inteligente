package com.freemanpivo.gestorponto.repositories

import com.freemanpivo.gestorponto.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface LancamentoRepository : MongoRepository<Lancamento, String> {
    fun finByFuncionarioId(funcionarioId: String, pageable: Pageable): Page<Lancamento>
}