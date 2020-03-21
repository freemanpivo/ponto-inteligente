package com.freemanpivo.gestorponto.services.implementation

import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.enums.PerfilEnum
import com.freemanpivo.gestorponto.exception.PersistenceException
import com.freemanpivo.gestorponto.repositories.FuncionarioRepository
import com.freemanpivo.gestorponto.utils.SenhaUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @Autowired
    val funcionarioService: FuncionarioService? = null

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    private val CPF: String = "15765200800"
    private val ID: String = "1"
    private val EMAIL: String = "alambrado@amaral.com"

    private fun montaFuncionario(): Funcionario = Funcionario(
            "Alambrado Amaral",
            EMAIL,
            SenhaUtils().gerarBCrypt("123456"),
            CPF,
            PerfilEnum.ROLE_USUARIO,
            ID
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        BDDMockito.given(funcionarioRepository?.findByEmail(EMAIL)).willReturn(montaFuncionario())
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java))).willReturn(montaFuncionario())
    }

    @Test
    fun whenSavingAFuncionario_thenReturnTheFuncionario() {
        val funcionario: Funcionario? = funcionarioService?.persistir(montaFuncionario())
        println(funcionario.toString())
        Assert.assertNotNull(funcionario)
    }

    @Test(expected = PersistenceException::class)
    fun whenSavingAnExistingFuncionario_thenAnExceptionIsThrown() {
        BDDMockito.given(funcionarioRepository?.findByCpf(CPF)).willReturn(montaFuncionario())
        val funcionario: Funcionario? = funcionarioService?.persistir(montaFuncionario())
    }
}
