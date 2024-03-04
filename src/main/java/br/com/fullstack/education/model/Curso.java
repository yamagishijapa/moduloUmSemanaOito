package br.com.fullstack.education.model;

import lombok.AccessLevel;
import lombok.Setter;

public class Curso {

    private static int proximoId = 1;

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

    public static int proximoId() {
        return proximoId;
    }
}
