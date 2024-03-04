package br.com.fullstack.education.services;

import br.com.fullstack.education.model.Curso;
import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    AlunoService alunoService;

    public ResponseEntity<?> cadastrarCurso(String nome, String descricao, int cargaHoraria) {
        try {
            validar(nome, cargaHoraria);
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Erro ao cadastrar curso!", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }

        Curso curso = new Curso(nome, descricao, cargaHoraria);
        Curso.cadastrar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    public List<Curso> consultarCursos() {
        return Curso.getCursosCadastrados();
    }

    public Optional<Curso> encontrarCursoPorId(int id) {
        return consultarCursos().stream()
                .filter(curso -> curso.getId() == id)
                .findFirst();
    }

    public ResponseEntity<?> matricularAlunoNoCurso(Integer idCurso, Integer idAluno) {

        try {
            Aluno alunoMatricula = checaSeAlunoExiste(idAluno);
            Curso cursoMatricula = checaSeCursoExiste(idCurso);
            cursoMatricula.getAlunosMatriculados().add(alunoMatricula);
            return ResponseEntity.ok("Aluno '" + alunoMatricula.getNome() +  "' matriculado com sucesso no curso '"  + cursoMatricula.getNome() + "'");

        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Erro ao matricular aluno no curso!", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
    }

    private Aluno checaSeAlunoExiste(Integer idAluno) {
        return alunoService.encontrarAlunoPorId(idAluno)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    private Curso checaSeCursoExiste(Integer idCurso) {
        return encontrarCursoPorId(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));
    }

    private void validar(String nome, Integer cargaHoraria) throws RuntimeException {
        if (nome == null || nome.isBlank()) {
            throw new RuntimeException("Nome do curso é obrigatório!");
        }

        if (cargaHoraria == null || cargaHoraria.equals(0)) {
            throw new RuntimeException("Carga horária do curso é obrigatória e seu valor deve ser diferente de 0!");
        }
    }
}
