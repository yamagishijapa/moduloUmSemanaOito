package br.com.fullstack.education;

import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.model.Curso;
import br.com.fullstack.education.services.AlunoService;
import br.com.fullstack.education.services.CursoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ModuloUmSemanaOitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloUmSemanaOitoApplication.class, args);
	}

}
