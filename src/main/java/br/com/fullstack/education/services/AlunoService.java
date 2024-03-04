package br.com.fullstack.education.services;

import br.com.fullstack.education.model.Aluno;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoService {

    public Aluno cadastrarAluno(String nome, LocalDate dataNascimento) {
        Aluno aluno = new Aluno(nome, dataNascimento);
        Aluno.cadastrar(aluno);
        return aluno;
    }

    public List<Aluno> consultarAlunos() {
        return Aluno.getAlunosCadastrados();
    }
}
