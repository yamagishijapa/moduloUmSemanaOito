package br.com.fullstack.education.services;

import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    public ResponseEntity<?> cadastrarAluno(String nome, String dataNascimento) {
        LocalDate dataNascimentoFormat = null;
        try {
            validar(nome, dataNascimento);
            dataNascimentoFormat = validaFormatoDataNascimento(dataNascimento);
        } catch (ResponseStatusException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Erro ao cadastrar aluno!", e.getReason());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        } catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Erro ao cadastrar aluno!", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
        }
        Aluno aluno = new Aluno(nome, dataNascimentoFormat);
        Aluno.cadastrar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    public List<Aluno> consultarAlunos() {
        return Aluno.getAlunosCadastrados();
    }

    public Optional<Aluno> encontrarAlunoPorId(int id) {
        return consultarAlunos().stream()
                .filter(aluno -> aluno.getId() == id)
                .findFirst();
    }

    private void validar(String nome, String dataNascimento) throws RuntimeException {
        if (nome == null || nome.isBlank()) {
            throw new RuntimeException("Nome do aluno é obrigatório!");
        }

        if (dataNascimento == null) {
            throw new RuntimeException("Data de nascimento do aluno é obrigatória!");
        }

        try {
            LocalDate.parse(dataNascimento);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de nascimento deve estar no formato yyyy-MM-dd", e);
        }
    }

    private LocalDate validaFormatoDataNascimento(String dataNascimento){
        try {
            return LocalDate.parse(dataNascimento);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Data de nascimento do aluno deve estar no formato yyyy-MM-dd! Ex.: 1997-11-16");
        }
    }


}
