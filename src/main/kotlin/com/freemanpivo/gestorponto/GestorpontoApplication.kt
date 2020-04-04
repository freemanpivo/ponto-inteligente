package com.freemanpivo.gestorponto

import com.freemanpivo.gestorponto.documents.Empresa
import com.freemanpivo.gestorponto.documents.Funcionario
import com.freemanpivo.gestorponto.enums.PerfilEnum
import com.freemanpivo.gestorponto.repositories.EmpresaRepository
import com.freemanpivo.gestorponto.repositories.FuncionarioRepository
import com.freemanpivo.gestorponto.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.prefs.Preferences

@SpringBootApplication
class GestorpontoApplication(val empresaRepository: EmpresaRepository,
							 val funcionarioRepository: FuncionarioRepository) : CommandLineRunner {
	override fun run(vararg args: String?) {
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()

		val empresa: Empresa = Empresa("Empresa1", "10443887000146")
		empresaRepository.save(empresa)

		val admin: Funcionario = Funcionario(
				"Administrador",
				"admin@admin.com",
				SenhaUtils().gerarBCrypt("123456"),
				"257083117000",
				PerfilEnum.ROLE_ADMIN,
				empresa.id!!
		)
		funcionarioRepository.save(admin)

		val funcionario: Funcionario = Funcionario(
				"NomeFuncionario",
				"funcionario@empresa.com",
				SenhaUtils().gerarBCrypt("123456"),
				"44325441557",
				PerfilEnum.ROLE_USUARIO,
				empresa.id!!
		)
		funcionarioRepository.save(funcionario)

		System.out.println("EMPRESA ID: " + empresa.id)
		System.out.println("ADMIN ID: " + admin.id)
		System.out.println("FUNCIONARIO ID: " + funcionario.id)

	}
}

fun main(args: Array<String>) {
	runApplication<GestorpontoApplication>(*args)
}
