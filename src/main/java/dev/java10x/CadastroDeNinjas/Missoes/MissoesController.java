package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    public MissoesController(MissoesService missoes_service) {
        this.missoes_service = missoes_service;
    }

    private final MissoesService missoes_service;

    // GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> ListarMissoes() {
        List<MissoesDTO> lista_missoes = missoes_service.ListarMissoes();
        return ResponseEntity.ok(lista_missoes);
    }

    // GET -- Madanr uma requisição para listar uma missao por id
    @GetMapping("/listarID/{id}")
    public ResponseEntity<String> ListarMissaoPorId(@PathVariable Long id){
        if (missoes_service.ListarMissaoPorId(id) != null)
        {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Missão com id " + id + " encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id não encontrado.");
        }
    }

    // Post -- Mandar uma requisiçao para criar as missoes
    @PostMapping("/criar")
    public ResponseEntity<String> CriarMissao(@RequestBody MissoesDTO missao) {
        missoes_service.CriarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Nova missão cadastrada com sucesso!");
    }

    // PUT -- Mandar uma requisiçao para alterar as missoes
    @PutMapping("/alterarID/{id}")
    public ResponseEntity<String> AlterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao_atualizada) {
        if (missoes_service.ListarMissaoPorId(id) != null)
        {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Missão com id " + id + " alterada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id não encontrado.");
        }
    }

    // Delete -- Mandar uma requisiçao para deletar as missoes
    @DeleteMapping("/deletarID/{id}")
    public ResponseEntity<String> DeletarMissao(@PathVariable Long id) {
        if (missoes_service.ListarMissaoPorId(id) != null)
        {
            missoes_service.DeletarMissao(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Missão com id " + id + " deletada!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id não encontrado.");
        }
    }
}