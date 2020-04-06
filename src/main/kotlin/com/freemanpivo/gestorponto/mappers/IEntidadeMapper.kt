package com.freemanpivo.gestorponto.mappers

interface IEntidadeMapper<E,D> {
    fun converterEntidadeParaDto(entidade: E): D
    fun converterDtoParaEntidade(dto: D): E
    fun converterEntidadesParaDtos(): List<D> { return listOf() }
    fun converterDtosParaEntidades(): List<E> { return listOf() }
}