package br.com.fullstack.education;

import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.services.AlunoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ModuloUmSemanaOitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloUmSemanaOitoApplication.class, args);
		AlunoService alunoService = new AlunoService();

		alunoService.cadastrarAluno("Thiago", LocalDate.of(1997, 11, 16));
		alunoService.cadastrarAluno("Maria Julia", LocalDate.of(1997, 5, 30));

		System.out.println("Alunos Cadastrados:");
		for (Aluno aluno : alunoService.consultarAlunos()) {
			System.out.println(aluno.toString());
		}
	}

}
