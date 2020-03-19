package com.freemanpivo.gestorponto.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtils {
    fun gerarBCrypt(senha: String) : String = BCryptPasswordEncoder().encode(senha)
}