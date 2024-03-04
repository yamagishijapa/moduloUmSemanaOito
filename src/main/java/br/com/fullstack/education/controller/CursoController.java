package br.com.fullstack.education.controller;

import br.com.fullstack.education.model.Curso;
import br.com.fullstack.education.requests.CursoRequest;
import br.com.fullstack.education.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody CursoRequest cursoRequest) {
        Curso cursoCadastrado = cursoService.cadastrarCurso(cursoRequest.getNome(), cursoRequest.getDescricao(), cursoRequest.getCargaHoraria());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCadastrado);
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Curso>> consultarCursos() {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.consultarCursos());
    }
}
