package br.com.fullstack.education.controller;

import br.com.fullstack.education.model.Aluno;
import br.com.fullstack.education.requests.AlunoRequest;
import br.com.fullstack.education.response.ErrorResponse;
import br.com.fullstack.education.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Tag(name = "Alunos", description = "API para cadastro e consulta de alunos no sistema.")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @PostMapping("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para inlcusão de um aluno no sistema.", required = true, content =
    @Content(mediaType = "application/json",
            schema = @Schema(implementation = AlunoRequest.class),
            examples = @ExampleObject(value = "{" +
                    "\"nome\":\"Nome do Aluno\"," +
                    "\"dataNascimento\":\"2000-12-24\"}")))
    @Operation(summary = "Este endpoint cadastra um aluno no sistema.",
            description = "Para cadastrar um aluno no sistema, basta preencher os dados do aluno listados no Request Body", responses = {
            @ApiResponse(responseCode = "200", description = "Aluno cadastrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar o cadastro.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoRequest alunoRequest) {

        return alunoService.cadastrarAluno(alunoRequest.getNome(), alunoRequest.getDataNascimento());
    }

    @GetMapping("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Este endpoint consulta os alunos cadastrados no sistema.",
            description = "Para consultar os alunos do sistema, basta realizar a chamada GET", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos cadastrado no sistema:", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Aluno.class)))),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a consulta.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<List<Aluno>> consultarAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.consultarAlunos());
    }
}
