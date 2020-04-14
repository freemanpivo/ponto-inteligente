package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.documents.Lancamento
import com.freemanpivo.gestorponto.enums.TipoEnum
import com.freemanpivo.gestorponto.exception.ResourceNotFoundException
import com.freemanpivo.gestorponto.repositories.LancamentoRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

@RunWith(SpringRunner::class)
@SpringBootTest
class LancamentoServiceTest {

    @Autowired
    val lancamentoService: LancamentoService? = null

    @MockBean
    private val lancamentoRepository: LancamentoRepository? = null

    private val ID = "1"

    private fun montaLancamento(): Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO, id = ID, funcionarioId = ID)
    private fun montaOptionalLancamento(): Optional<Lancamento> = Optional.of(montaLancamento())

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito
                .given(lancamentoRepository?.findByFuncionarioId(ID, PageRequest.of(0, 10)))
                .willReturn(PageImpl(ArrayList<Lancamento>()))

    }

    @Test
    fun findLancamentoByFuncionarioId_ThenReturnLancamento() {
        val lancamento: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(ID, PageRequest.of(0, 10))
        Assert.assertNotNull(lancamento)
    }

    @Test
    fun registerALancamento_ThenReturnTheSameLancamento() {
        BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java))).willReturn(montaLancamento())

        val lancamento: Lancamento? = lancamentoService?.persistir(montaLancamento())
        Assert.assertNotNull(lancamento)
    }

    @Test
    fun findPreviouslyRegisteredLancamentoById_ThenReturnLancamento() {
        BDDMockito.given(lancamentoRepository?.findById(ID)).willReturn(montaOptionalLancamento())

        val lancamento: Lancamento? = lancamentoService?.buscarPorId(ID)
        Assert.assertNotNull(lancamento)
    }

    @Test(expected = ResourceNotFoundException::class)
    fun findNonExistentLancamento_ThenThrowResourceNotFound() {
        BDDMockito.given(lancamentoRepository?.findById(ID)).willReturn(Optional.empty())

        val lancamento: Lancamento? = lancamentoService?.buscarPorId(ID)
        Assert.assertNull(lancamento)
    }

    @Test
    fun findPreviouslyRegisteredLancamento_ThenRemoveItem() {
        BDDMockito.given(lancamentoRepository?.findById(ID)).willReturn(montaOptionalLancamento())

        lancamentoService?.remover(ID)
    }

    @Test(expected = ResourceNotFoundException::class)
    fun tryToRemoveNonExistentLancamento_ThenResourceNotFoundIsThrown() {
        BDDMockito.given(lancamentoRepository?.findById(ID)).willReturn(Optional.empty())

        lancamentoService?.remover(ID)
    }


}