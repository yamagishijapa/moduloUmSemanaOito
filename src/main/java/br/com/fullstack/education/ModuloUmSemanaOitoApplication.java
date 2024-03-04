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
		AlunoService alunoService = new AlunoService();
		CursoService cursoService = new CursoService();

		alunoService.cadastrarAluno("Thiago", LocalDate.of(1997, 11, 16));
		alunoService.cadastrarAluno("Maria Julia", LocalDate.of(1997, 5, 30));

		System.out.println("Alunos Cadastrados:");
		for (Aluno aluno : alunoService.consultarAlunos()) {
			System.out.println(aluno.toString());
		}

		cursoService.cadastrarCurso("Backend", "Curso completo de Backend", 400);
		cursoService.cadastrarCurso("Frontend", "Curso completo de Frontend", 400);

		System.out.println("Cursos Cadastrados:");
		for (Curso curso : cursoService.consultarCursos()) {
			System.out.println(curso.toString());
		}
	}

}
