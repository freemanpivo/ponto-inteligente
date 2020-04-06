package com.freemanpivo.gestorponto.mappers

import com.freemanpivo.gestorponto.documents.Lancamento
import com.freemanpivo.gestorponto.dto.LancamentoDto
import com.freemanpivo.gestorponto.enums.TipoEnum
import java.text.SimpleDateFormat

class LancamentoMapper : IEntidadeMapper<Lancamento, LancamentoDto> {
    private val dateFormat = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")

    override fun converterEntidadeParaDto(entidade: Lancamento): LancamentoDto = LancamentoDto(
            dateFormat.format(entidade.data),
            entidade.tipo.toString(),
            entidade.descricao,
            entidade.localizacao,
            entidade.funcionarioId,
            entidade.id
    )


    override fun converterDtoParaEntidade(dto: LancamentoDto): Lancamento = Lancamento(
            dateFormat.parse(dto.dataRegistroPonto),
            TipoEnum.valueOf(dto.tipo!!),
            dto.descricao,
            dto.localizacao,
            dto.funcionarioId!!,
            dto.id
    )


}