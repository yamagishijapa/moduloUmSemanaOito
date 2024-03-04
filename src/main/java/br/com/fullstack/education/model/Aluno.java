package br.com.fullstack.education.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class Aluno {
    private static int proximoId = 1;
    @Getter
    private static List<Aluno> alunosCadastrados = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private final int id;
    private String nome;
    private LocalDate dataNascimento;

    public Aluno(String nome, LocalDate dataNascimento) {
        this.id = proximoId++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public static int proximoId() {
        return proximoId;
    }

    public static void cadastrar(Aluno aluno) {
        alunosCadastrados.add(aluno);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nome: " + nome +
                ", Data de Nascimento: " + dataNascimento;
    }
}
