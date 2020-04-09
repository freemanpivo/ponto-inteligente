package com.freemanpivo.gestorponto.controllers

import com.freemanpivo.gestorponto.documents.Lancamento
import com.freemanpivo.gestorponto.dto.FuncionarioDto
import com.freemanpivo.gestorponto.dto.LancamentoDto
import com.freemanpivo.gestorponto.mappers.LancamentoMapper
import com.freemanpivo.gestorponto.response.Response
import com.freemanpivo.gestorponto.services.implementation.FuncionarioService
import com.freemanpivo.gestorponto.services.implementation.LancamentoService
import com.freemanpivo.gestorponto.validators.DtoValidator
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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

        var lancamento: Lancamento = LancamentoMapper().converterDtoParaEntidade(lancamentoDto)
        lancamento = lancamentoService.persistir(lancamento)

        resposta.body = LancamentoMapper().converterEntidadeParaDto(lancamento)
        return ResponseEntity.ok(resposta)
    }

    @GetMapping(value = "/{id}")
    fun listarLancamentoPorId(@PathVariable("id") id: String) : ResponseEntity<Response<LancamentoDto>> {
        val resposta: Response<LancamentoDto> = Response<LancamentoDto>()
        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

        if (lancamento == null) {
            resposta.errors.add("Lancamento não encontrado para o id - $id")
            return ResponseEntity.badRequest().body(resposta)
        }

        resposta.body = LancamentoMapper().converterEntidadeParaDto(lancamento)
        return ResponseEntity.ok(resposta)
    }

    @GetMapping(value = "/funcionario/{funcionarioId}")
    fun listarLancamentosPorFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                                          @RequestParam(value = "pag", defaultValue = "0") pagina: Int,
                                          @RequestParam(value = "ord", defaultValue = "id") ordem: String,
                                          @RequestParam(value = "dir", defaultValue = "DESC") direcao: String) :
            ResponseEntity<Response<Page<LancamentoDto>>> {
        val resposta: Response<Page<LancamentoDto>> = Response<Page<LancamentoDto>>()
        val configuracaoPagina: PageRequest =
                PageRequest(pagina, quantidadeItensPagina, Sort.Direction.valueOf(direcao), ordem)
        val lancamentos: Page<Lancamento> = lancamentoService.buscarPorFuncionarioId(funcionarioId, configuracaoPagina)
        val lancamentosDto: Page<LancamentoDto> =
                lancamentos.map { lancamento -> LancamentoMapper().converterEntidadeParaDto(lancamento) }

        resposta.body = lancamentosDto
        return ResponseEntity.ok(resposta)
    }

    @PutMapping(value = "/{id}")
    fun atualizarLancamento(@PathVariable("id") id: String,
                            @Valid @RequestBody lancamentoDto: LancamentoDto,
                            resultado: BindingResult): ResponseEntity<Response<LancamentoDto>> {
        val resposta: Response<LancamentoDto> = Response()
        DtoValidator(funcionarioService).validarFuncionarioEfetuouLancamento(lancamentoDto, resultado)
        lancamentoDto.id = id
        var lancamento: Lancamento = LancamentoMapper().converterDtoParaEntidade(lancamentoDto)

        if (resultado.hasErrors()) {
            for (erro in resultado.allErrors)
                resposta.errors.add(erro.defaultMessage.toString())
            return ResponseEntity.badRequest().body(resposta)
        }

        lancamento = lancamentoService.persistir(lancamento)
        resposta.body = LancamentoMapper().converterEntidadeParaDto(lancamento)

        return ResponseEntity.ok(resposta)
    }

    @DeleteMapping(value = "/{id}")
    fun removerLancamento(@PathVariable("id") id: String): ResponseEntity<Response<String>> {
        val resposta: Response<String> = Response()
        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

        if (lancamento == null) {
            resposta.errors.add("Erro ao remover lançamento. ID $id não encontrado.")
            return ResponseEntity.badRequest().body(resposta)
        }

        lancamentoService.remover(id)
        return ResponseEntity.ok(Response())
    }


}