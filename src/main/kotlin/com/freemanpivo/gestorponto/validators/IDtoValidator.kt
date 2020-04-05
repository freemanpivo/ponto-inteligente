package com.freemanpivo.gestorponto.validators

import com.freemanpivo.gestorponto.dto.LancamentoDto
import org.springframework.validation.BindingResult

interface IDtoValidator {
    fun validarFuncionarioEfetuouLancamento(lancamentoDto: LancamentoDto, resultado: BindingResult)
}