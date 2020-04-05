package com.freemanpivo.gestorponto.validators

import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.dto.LancamentoDto
import com.freemanpivo.gestorponto.services.implementation.FuncionarioService
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

class DtoValidator (val funcionarioService: FuncionarioService) : IDtoValidator{
    override fun validarFuncionarioEfetuouLancamento(lancamentoDto: LancamentoDto, resultado: BindingResult) {
        if (lancamentoDto.funcionarioId == null) {
            resultado.addError(ObjectError("funcionario", "Nenhum funcionário foi informado."))
            return
        }

        val funcionario: Funcionario? = funcionarioService.buscarPorId(lancamentoDto.funcionarioId)
        if (funcionario == null) {
            resultado.addError(ObjectError("funcionario", "O funcionário não foi encontrado."))
        }
    }
}