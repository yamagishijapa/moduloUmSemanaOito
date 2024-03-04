package br.com.fullstack.education.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class Aluno {
    private static int proximoId = 1;

    @Setter(AccessLevel.NONE) private final int id;
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

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
