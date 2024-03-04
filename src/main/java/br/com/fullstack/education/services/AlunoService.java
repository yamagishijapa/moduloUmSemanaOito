package br.com.fullstack.education.services;

import br.com.fullstack.education.model.Aluno;

import java.time.LocalDate;
import java.util.List;

public class AlunoService {

    public void cadastrarAluno(String nome, LocalDate dataNascimento) {
        Aluno aluno = new Aluno(nome, dataNascimento);
        Aluno.cadastrar(aluno);
    }

    public List<Aluno> consultarAlunos() {
        return Aluno.getAlunosCadastrados();
    }
}
