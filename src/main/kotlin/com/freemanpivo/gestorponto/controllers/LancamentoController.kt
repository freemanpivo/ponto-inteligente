package com.freemanpivo.gestorponto.controllers

import com.freemanpivo.gestorponto.dto.FuncionarioDto
import com.freemanpivo.gestorponto.dto.LancamentoDto
import com.freemanpivo.gestorponto.response.Response
import com.freemanpivo.gestorponto.services.implementation.FuncionarioService
import com.freemanpivo.gestorponto.services.implementation.LancamentoService
import com.freemanpivo.gestorponto.validators.DtoValidator
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/lancamentos")

class LancamentoController (val lancamentoService: LancamentoService, val funcionarioService: FuncionarioService) {

    @Value("\${paginacao.quantidade_itens_pagina}")
    val quantidadeItensPagina: Int =100

    @PostMapping
    fun adicionar(
            @Valid @RequestBody lancamentoDto: LancamentoDto,
            resultado: BindingResult
    ) : ResponseEntity<Response<LancamentoDto>> {

        val resposta: Response<LancamentoDto> = Response()
        DtoValidator(funcionarioService).validarFuncionarioEfetuouLancamento(lancamentoDto, resultado)

        if (resultado.hasErrors()) {
            for (erro in resultado.allErrors)
                resposta.errors.add(erro.defaultMessage.toString())

            return ResponseEntity.badRequest().body(resposta)
        }
        resposta.body = lancamentoDto
        return ResponseEntity.ok(resposta)
    }

    @GetMapping
    fun listarTodos() : ResponseEntity<Response<FuncionarioDto>> {

        val resposta: Response<FuncionarioDto> = Response<FuncionarioDto>()

        return ResponseEntity.ok(resposta)
    }
}