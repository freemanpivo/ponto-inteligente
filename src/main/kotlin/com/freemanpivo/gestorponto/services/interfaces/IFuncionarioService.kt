package com.freemanpivo.gestorponto.services.interfaces

import com.freemanpivo.gestorponto.documents.Funcionario

interface IFuncionarioService {
    fun persistir(funcionario: Funcionario) : Funcionario
    fun buscarPorCpf(cpf: String) : Funcionario?
    fun buscarPorEmail(email: String) : Funcionario?
    fun buscarPorId(id: String) : Funcionario?
}