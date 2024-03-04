package br.com.fullstack.education.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private static int proximoId = 1;
    @Getter
    private static List<Curso> cursosCadastrados = new ArrayList<>();

    @Setter(AccessLevel.NONE) private final int id;
    private String nome;
    private String descricao;
    private int cargaHoraria;

    public Curso(String nome, String descricao, int cargaHoraria) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public static void cadastrar(Curso curso) {
        cursosCadastrados.add(curso);
    }

    public static int proximoId() {
        return proximoId;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nome: " + nome +
                ", Descricao: " + descricao +
                ", cargaHoraria: " + cargaHoraria;
    }
}
