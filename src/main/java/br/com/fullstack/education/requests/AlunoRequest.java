package br.com.fullstack.education.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequest {

    private Integer id;
    private String nome;
    private String dataNascimento;
}
