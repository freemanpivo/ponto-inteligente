package com.freemanpivo.gestorponto.repositories

import com.freemanpivo.gestorponto.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository

interface FuncionarioRepository : MongoRepository<Funcionario, String> {
    fun findByCpf(cpf: String) : Funcionario
    fun findByEmail(email: String) : Funcionario
}