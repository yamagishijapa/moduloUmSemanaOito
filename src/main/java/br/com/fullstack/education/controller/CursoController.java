package br.com.fullstack.education.controller;

import br.com.fullstack.education.model.Curso;
import br.com.fullstack.education.requests.AlunoRequest;
import br.com.fullstack.education.requests.CursoRequest;
import br.com.fullstack.education.response.ErrorResponse;
import br.com.fullstack.education.services.AlunoService;
import br.com.fullstack.education.services.CursoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Serviço para cadastro e consulta de cursos e alunos no sistema. Também utilizado para matrícular alunos em cursos."), servers = {
        @Server(url = "http://localhost:8080/", description = "local")})
@Tag(name = "Cursos", description = "API para cadastro e consulta de cursos e realização de matrículas.")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Autowired
    AlunoService alunoService;

    @PostMapping("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestBody(description = "Dados para inlcusão de um curso no sistema.", required = true, content =
    @Content(mediaType = "application/json",
            schema = @Schema(implementation = CursoRequest.class),
            examples = @ExampleObject(value = "{" +
                    "\"nome\":\"Nome do Curso\"," +
                    "\"descricao\":\"Descrição do Curso\"," +
                    "\"cargaHoraria\":100}")))
    @Operation(summary = "Este endpoint cadastra um curso no sistema.",
            description = "Para cadastrar um curso no sistema, basta preencher os dados do curso listados no Request Body", responses = {
            @ApiResponse(responseCode = "200", description = "Curso cadastrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar o cadastro.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<?> cadastrarCurso(@org.springframework.web.bind.annotation.RequestBody CursoRequest cursoRequest) {
        return cursoService.cadastrarCurso(cursoRequest.getNome(), cursoRequest.getDescricao(), cursoRequest.getCargaHoraria());
    }

    @GetMapping("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Este endpoint consulta os cursos cadastrados no sistema.",
            description = "Para consultar os cursos do sistema, basta realizar a chamada GET", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos cadastrado no sistema:", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Curso.class)))),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a consulta.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<List<Curso>> consultarCursos() {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.consultarCursos());
    }

    @PostMapping("/{idCurso}/matricular")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestBody(description = "Dados para realizar a matrícula de um aluno no curso no especificado no sistema.", required = true, content =
    @Content(mediaType = "application/json",
            schema = @Schema(implementation = AlunoRequest.class),
            examples = @ExampleObject(value = "{" +
                    "\"id\": 1 }")))
    @Operation(summary = "Este endpoint matricula um aluno em um curso.",
            description = "Para matricular o aluno no curso, basta preencher o id do aluno na request body e o id do curso no path", responses = {
            @ApiResponse(responseCode = "200", description = "Aluno matriculado com sucesso no curso."),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a matrícula.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<?> matricularAlunoNoCurso(@PathVariable Integer idCurso, @org.springframework.web.bind.annotation.RequestBody AlunoRequest aluno) {

        return cursoService.matricularAlunoNoCurso(idCurso, aluno.getId());
    }
}
