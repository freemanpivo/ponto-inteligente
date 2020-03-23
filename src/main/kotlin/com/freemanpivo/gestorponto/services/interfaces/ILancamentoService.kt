package com.freemanpivo.gestorponto.services.interfaces

import com.freemanpivo.gestorponto.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ILancamentoService {

    fun buscarPorFuncionarioId(funcionarioId: String, pagerequest: PageRequest) : Page<Lancamento>
    fun buscarPorId(id: String): Lancamento?
    fun persistir(lancamento: Lancamento): Lancamento
    fun remover(id: String)

}