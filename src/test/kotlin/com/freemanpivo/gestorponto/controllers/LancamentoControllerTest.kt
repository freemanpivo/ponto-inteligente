package com.freemanpivo.gestorponto.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.documents.Lancamento
import com.freemanpivo.gestorponto.dto.LancamentoDto
import com.freemanpivo.gestorponto.enums.PerfilEnum
import com.freemanpivo.gestorponto.enums.TipoEnum
import com.freemanpivo.gestorponto.services.implementation.FuncionarioService
import com.freemanpivo.gestorponto.services.implementation.LancamentoService
import com.freemanpivo.gestorponto.utils.SenhaUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class LancamentoControllerTest {
    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val funcionarioService: FuncionarioService? = null

    @MockBean
    private val lancamentoService: LancamentoService? = null

    private val url: String = "/api/lancamentos/"
    private val idFuncionario: String = "1"
    private val idLancamento: String = "1"
    private val tipoLancamento: String = TipoEnum.INICIO_TRABALHO.name
    private val dataLancamento: Date = Date()
    private val formatoData = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    //TESTS GOES HERE!
    //1. Test register lancamento successful
    //2. Test register lancamento invalid funcionarioId
    //3. Test remove Lancamento
    @Test
    @Throws(Exception::class)
    @WithMockUser
    fun testSaveFuncionario_expectResponse200() {
        val lancamento: Lancamento = montarObjetoLancamento()

        BDDMockito.given(funcionarioService?.buscarPorId(idFuncionario)).willReturn(montarObjetoFuncionario())
        BDDMockito.given(lancamentoService?.persistir(montarObjetoLancamento())).willReturn(lancamento)

        mvc!!.perform(MockMvcRequestBuilders.post(url)
                .content(parseJsonAsString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.body.tipo").value(tipoLancamento))
                .andExpect(jsonPath("$.body.data").value(formatoData.format(dataLancamento)))
                .andExpect(jsonPath("$.body.funcionarioId").value(idFuncionario))
                .andExpect(jsonPath("$.errors").isEmpty)

    }


    private fun montarObjetoLancamento(): Lancamento =
            Lancamento(dataLancamento, TipoEnum.valueOf(tipoLancamento),"descricao",
                    funcionarioId = idFuncionario, localizacao = "1.243,4.345")

    private fun montarObjetoFuncionario(): Funcionario =
            Funcionario("Beltrano Bezerra", "beltrano@bezerra.com",
                    SenhaUtils().gerarBCrypt("123456"),"34774800074",
                    PerfilEnum.ROLE_USUARIO, id = idFuncionario)

    private fun parseJsonAsString(): String {
        val lancamentoDto: LancamentoDto =
                LancamentoDto(formatoData.format(dataLancamento), tipoLancamento, "descricao",
                        "1.234,4.234", idFuncionario)
        val mapper = ObjectMapper()

        return mapper.writeValueAsString(lancamentoDto)
    }
}