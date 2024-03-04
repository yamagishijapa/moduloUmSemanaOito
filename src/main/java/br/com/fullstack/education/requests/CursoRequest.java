package br.com.fullstack.education.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequest {

    private String nome;
    private String descricao;
    private int cargaHoraria;
}
