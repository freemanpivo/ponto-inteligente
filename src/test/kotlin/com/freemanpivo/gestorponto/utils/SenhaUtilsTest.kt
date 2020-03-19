package com.freemanpivo.gestorponto.utils

import org.junit.Assert
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {
    private val SENHA = "123456"
    private val bCryptEncoder =  BCryptPasswordEncoder()

    @Test
    fun testGerarHashDaSenha() {
        val hash = SenhaUtils().gerarBCrypt(SENHA)
        Assert.assertTrue(bCryptEncoder.matches(SENHA, hash))
    }
}