package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.exception.PersistenceException
import com.freemanpivo.gestorponto.exception.ResourceNotFoundException
import com.freemanpivo.gestorponto.repositories.FuncionarioRepository
import com.freemanpivo.gestorponto.services.interfaces.IFuncionarioService
import org.springframework.stereotype.Service
import java.util.*

@Service
class FuncionarioService(val funcionarioRepository: FuncionarioRepository) : IFuncionarioService {

    @Throws(PersistenceException::class)
    override fun persistir(funcionario: Funcionario): Funcionario {
        val isFuncionario: Funcionario? = funcionarioRepository.findByCpf(funcionario.cpf)
        if (isFuncionario != null) {
            throw PersistenceException("Funcionario ja cadastrado")
        } else {
            return funcionarioRepository.save(funcionario)
        }
    }

    @Throws(ResourceNotFoundException::class)
    override fun buscarPorCpf(cpf: String): Funcionario? =
            funcionarioRepository.findByCpf(cpf) ?: throw ResourceNotFoundException("Funcionario Inexistente")

    @Throws(ResourceNotFoundException::class)
    override fun buscarPorEmail(email: String): Funcionario? =
            funcionarioRepository.findByEmail(email) ?: throw ResourceNotFoundException("Funcionario Inexistente")

    @Throws(ResourceNotFoundException::class)
    override fun buscarPorId(id: String): Funcionario? {
        val funcionario: Optional<Funcionario> = funcionarioRepository.findById(id)
        if (funcionario.isEmpty) {
            throw ResourceNotFoundException("Funcionario Inexistente")
        } else {
            return funcionario.get()
        }
    }

}