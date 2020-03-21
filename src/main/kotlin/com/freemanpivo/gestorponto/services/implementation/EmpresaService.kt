package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Empresa
import com.freemanpivo.gestorponto.repositories.EmpresaRepository
import com.freemanpivo.gestorponto.services.interfaces.IEmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaService(val empresaRepository: EmpresaRepository) : IEmpresaService {
    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)
}