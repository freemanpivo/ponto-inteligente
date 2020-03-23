package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Lancamento
import com.freemanpivo.gestorponto.exception.ResourceNotFoundException
import com.freemanpivo.gestorponto.repositories.LancamentoRepository
import com.freemanpivo.gestorponto.services.interfaces.ILancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class LancamentoService(val lancamentoRepository: LancamentoRepository) : ILancamentoService{
    override fun buscarPorFuncionarioId(funcionarioId: String, pagerequest: PageRequest): Page<Lancamento>
            = lancamentoRepository.findByFuncionarioId(funcionarioId, pagerequest)

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    @Throws(ResourceNotFoundException::class)
    override fun buscarPorId(id: String): Lancamento? {
        val lancamento: Optional<Lancamento> = lancamentoRepository.findById(id)
        if (lancamento.isEmpty)
            throw ResourceNotFoundException("Lancamento Inexistente")

        return lancamento.get()
    }

    @Throws(ResourceNotFoundException::class)
    override fun remover(id: String) {
        val lancamento: Optional<Lancamento> = lancamentoRepository.findById(id)
        if (lancamento.isEmpty)
            throw ResourceNotFoundException("Lancamento Inexistente")

        lancamentoRepository.delete(lancamento.get())
    }

}