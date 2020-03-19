package com.freemanpivo.gestorponto.services.interfaces

import com.freemanpivo.gestorponto.documents.Empresa

interface IEmpresaService {
    fun buscarPorCnpj(cnpj: String) : Empresa?
    fun persistir(empresa: Empresa) : Empresa
}