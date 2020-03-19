package com.freemanpivo.gestorponto.repositories

import com.freemanpivo.gestorponto.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository

interface EmpresaRepository : MongoRepository<Empresa, String> {
    fun findByCnpj(cnpj: String) : Empresa
}