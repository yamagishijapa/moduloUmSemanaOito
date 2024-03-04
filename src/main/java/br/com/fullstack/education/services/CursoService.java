package br.com.fullstack.education.services;

import br.com.fullstack.education.model.Curso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    public void cadastrarCurso(String nome, String descricao, int cargaHoraria) {
        Curso curso = new Curso(nome, descricao, cargaHoraria);
        Curso.cadastrar(curso);
    }

    public List<Curso> consultarCursos() {
        return Curso.getCursosCadastrados();
    }
}
