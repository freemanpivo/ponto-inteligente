package com.freemanpivo.gestorponto.repositories

import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface FuncionarioRepository : MongoRepository<Funcionario, String> {
    fun findByCpf(cpf: String) : Funcionario
}