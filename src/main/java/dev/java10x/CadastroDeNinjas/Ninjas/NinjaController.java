package dev.java10x.CadastroDeNinjas.Ninjas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninja_service;

    public NinjaController(NinjaService ninja_service) {
        this.ninja_service = ninja_service;
    }

    // =======================================================================
    // ROTA DE TESTE / BOAS-VINDAS
    // =======================================================================
    @GetMapping("/boasvindas")
    @Operation(
            summary = "Exibe uma mensagem de boas-vindas",
            description = """
                    Rota simples utilizada para testar se a aplicação está rodando corretamente. 
                    Retorna uma mensagem de texto confirmando o funcionamento da API.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Aplicação em funcionamento")
    public String BoasVindas() {
        return "Bem-vindo(a) à API de Cadastro de Ninjas! 🥷";
    }

    // =======================================================================
    // CRIAR NOVO NINJA (CREATE)
    // =======================================================================
    @PostMapping("/criar")
    @Operation(
            summary = "Cadastra um novo ninja",
            description = """
                    Cria um novo registro de ninja no banco de dados. 
                    O corpo da requisição deve conter as informações do ninja (nome, email, idade, rank, imagem e missão associada).
                    Caso a missão informada exista, o ninja será vinculado a ela.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados ou requisição inválida")
    })
    public ResponseEntity<String> CriarNinja(
            @RequestBody NinjaDTO ninja
    ) {
        NinjaDTO novo_ninja = ninja_service.CriarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("O ninja " + novo_ninja.getNome() + " foi cadastrado com sucesso! 🥷\nID: " + novo_ninja.getId());
    }

    // =======================================================================
    // LISTAR TODOS OS NINJAS (READ)
    // =======================================================================
    @GetMapping("/listar")
    @Operation(
            summary = "Lista todos os ninjas cadastrados",
            description = """
                    Retorna uma lista completa com todos os ninjas existentes no banco de dados.
                    Cada ninja inclui suas informações pessoais, rank e missão associada (caso exista).
                    """
    )
    @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso")
    public ResponseEntity<List<NinjaDTO>> ListarNinjas() {
        List<NinjaDTO> ninjas = ninja_service.ListarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // =======================================================================
    // LISTAR NINJA POR ID (READ)
    // =======================================================================
    @GetMapping("/listarID/{id}")
    @Operation(
            summary = "Busca um ninja pelo seu ID",
            description = """
                    Retorna as informações detalhadas de um ninja específico com base em seu ID.
                    Caso o ID informado não exista, retorna uma mensagem de erro.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado para o ID informado")
    })
    public ResponseEntity<String> ListarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja_encontrado = ninja_service.ListarNinjasPorId(id);
        if (ninja_encontrado != null) {
            return ResponseEntity.ok("Ninja " + ninja_encontrado.getNome() + " com o ID " + id + " foi encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não foi encontrado.");
        }
    }

    // =======================================================================
    // ATUALIZAR NINJA POR ID (UPDATE)
    // =======================================================================
    @PutMapping("/alterarID/{id}")
    @Operation(
            summary = "Atualiza as informações de um ninja existente",
            description = """
                    Atualiza os dados de um ninja já existente com base em seu ID.
                    O corpo da requisição deve conter as novas informações.
                    Caso o campo 'missoes' seja nulo, a missão atual do ninja será removida.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> AlterarNinjaPorId(
            @PathVariable Long id,
            @RequestBody NinjaDTO ninja_atualizado
    ) {
        if (ninja_service.ListarNinjasPorId(id) != null) {
            ninja_service.AtualizarNinja(id, ninja_atualizado);
            return ResponseEntity.ok("Ninja " + ninja_atualizado.getNome() +
                    " foi atualizado com sucesso! 🛠️\nID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não foi encontrado.");
        }
    }

    // =======================================================================
    // DELETAR NINJA POR ID (DELETE)
    // =======================================================================
    @DeleteMapping("/deletarID/{id}")
    @Operation(
            summary = "Remove um ninja do sistema",
            description = """
                    Exclui um ninja do banco de dados com base em seu ID.
                    Essa operação é irreversível.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> DeletarNinjaPorId(@PathVariable Long id) {
        if (ninja_service.ListarNinjasPorId(id) != null) {
            ninja_service.DeletarNinja(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " foi deletado com sucesso! 💀");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID " + id + " não foi encontrado.");
        }
    }
}
