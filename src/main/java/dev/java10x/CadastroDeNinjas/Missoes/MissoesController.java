package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoes_service;

    public MissoesController(MissoesService missoes_service) {
        this.missoes_service = missoes_service;
    }

    // =========================================================================
    // LISTAR TODAS AS MISSÕES (READ)
    // =========================================================================
    @GetMapping("/listar")
    @Operation(
            summary = "Lista todas as missões cadastradas",
            description = """
                    Retorna uma lista completa com todas as missões registradas no banco de dados.
                    Cada missão contém suas informações básicas, como nome, dificuldade e a lista de ninjas atribuídos.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso")
    public ResponseEntity<List<MissoesDTO>> ListarMissoes() {
        List<MissoesDTO> lista_missoes = missoes_service.ListarMissoes();
        return ResponseEntity.ok(lista_missoes);
    }

    // =========================================================================
    // LISTAR MISSÃO POR ID (READ)
    // =========================================================================
    @GetMapping("/listarID/{id}")
    @Operation(
            summary = "Busca uma missão pelo ID",
            description = """
                    Retorna os detalhes de uma missão específica com base em seu ID.
                    Caso o ID não seja encontrado, retorna uma mensagem de erro.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada para o ID informado")
    })
    public ResponseEntity<String> ListarMissaoPorId(@PathVariable Long id) {
        if (missoes_service.ListarMissaoPorId(id) != null) {
            return ResponseEntity.ok("Missão com ID " + id + " encontrada com sucesso! 🎯");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

    // =========================================================================
    // CRIAR NOVA MISSÃO (CREATE)
    // =========================================================================
    @PostMapping("/criar")
    @Operation(
            summary = "Cadastra uma nova missão",
            description = """
                    Cria uma nova missão no sistema.  
                    O corpo da requisição deve conter o nome da missão, sua dificuldade (classificação de 'A' a 'S', por exemplo),
                    e opcionalmente uma lista de ninjas que participarão dessa missão.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados para criação da missão")
    })
    public ResponseEntity<String> CriarMissao(@RequestBody MissoesDTO missao) {
        missoes_service.CriarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão '" + missao.getNome() + "' cadastrada com sucesso! 🚀");
    }

    // =========================================================================
    // ATUALIZAR MISSÃO POR ID (UPDATE)
    // =========================================================================
    @PutMapping("/alterarID/{id}")
    @Operation(
            summary = "Atualiza os dados de uma missão existente",
            description = """
                    Atualiza as informações de uma missão com base em seu ID.  
                    Caso a missão não exista, é retornado um erro 404.  
                    Campos como nome e dificuldade podem ser alterados.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<String> AlterarMissao(
            @PathVariable Long id,
            @RequestBody MissoesDTO missao_atualizada
    ) {
        if (missoes_service.ListarMissaoPorId(id) != null) {
            missoes_service.AtualizarMissao(id, missao_atualizada);
            return ResponseEntity.ok("Missão com ID " + id + " atualizada com sucesso! 🔧");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

    // =========================================================================
    // DELETAR MISSÃO POR ID (DELETE)
    // =========================================================================
    @DeleteMapping("/deletarID/{id}")
    @Operation(
            summary = "Remove uma missão do sistema",
            description = """
                    Exclui permanentemente uma missão do banco de dados com base em seu ID.  
                    Essa operação é irreversível e também desvincula todos os ninjas associados.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<String> DeletarMissao(@PathVariable Long id) {
        if (missoes_service.ListarMissaoPorId(id) != null) {
            missoes_service.DeletarMissao(id);
            return ResponseEntity.ok("Missão com ID " + id + " foi deletada com sucesso! 💣");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }
}
