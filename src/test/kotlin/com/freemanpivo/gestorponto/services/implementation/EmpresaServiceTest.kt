package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Empresa
import com.freemanpivo.gestorponto.repositories.EmpresaRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Exception

@RunWith(SpringRunner::class)
@SpringBootTest
class EmpresaServiceTest () {

    // Autowired is used because Test class can only have one constructor.
    @Autowired
    val empresaService: EmpresaService? = null

    @MockBean
    private val empresaRepository: EmpresaRepository? = null

    private val CNPJ = "33104195000179"

    private fun montaEmpresa(): Empresa = Empresa("Empresa Ltda", CNPJ,"1")

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(montaEmpresa())
        BDDMockito.given(empresaRepository?.save(montaEmpresa())).willReturn(montaEmpresa())
    }

    @Test
    fun testBuscarEmpresaPorCnpj() {
        val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
        Assert.assertNotNull(empresa)
    }

    @Test
    fun testPersistirUmaEmpresa() {
        val empresa: Empresa? = empresaService?.persistir(montaEmpresa())
        Assert.assertNotNull(empresa)
    }
}