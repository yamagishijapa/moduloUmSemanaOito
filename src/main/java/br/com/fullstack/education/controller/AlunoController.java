package br.com.fullstack.education.controller;

import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.requests.AlunoRequest;
import br.com.fullstack.education.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody AlunoRequest alunoRequest) {
        Aluno alunoCadastrado = alunoService.cadastrarAluno(alunoRequest.getNome(), alunoRequest.getDataNascimento());
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCadastrado);
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Aluno>> consultarAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.consultarAlunos());
    }
}
