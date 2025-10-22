package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    public NinjaController(NinjaService ninja_service) {
        this.ninja_service = ninja_service;
    }

    private final NinjaService ninja_service;

    @GetMapping("/boasvindas")
    public String BoasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    // @RequestBody faz com que o usuário envie um dado no qual vai ser serializado e inserido no BD
    @PostMapping("/criar")
    public ResponseEntity<String> CriarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novo_ninja = ninja_service.CriarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("O ninja " + novo_ninja.getNome() + " foi cadastrado com sucesso! \nID: " + novo_ninja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> ListarNinjas() {
        List<NinjaDTO> ninjas = ninja_service.ListarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar ninja por id  (READ)
    // {PathVariable} faz com que o usuario mande a variavel que seria o id, pelo caminho/url
    @GetMapping("/listarID/{id}")
    public ResponseEntity<String> ListarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja_encontrado = ninja_service.ListarNinjasPorId(id);
        if (ninja_service.ListarNinjasPorId(id) != null){
            ninja_service.ListarNinjasPorId(id);
            return ResponseEntity.ok("Ninja " + ninja_encontrado.getNome() + " com o ID " + id + " encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o id " + id + " não encontrado.");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID/{id}")
    public ResponseEntity<String> AlterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninja_atualizado) {
        if (ninja_service.ListarNinjasPorId(id) != null)
        {
            ninja_service.AtualizarNinja(id, ninja_atualizado);
            return ResponseEntity.status(HttpStatus.FOUND).body("Ninja " + ninja_atualizado.getNome() +
                    " foi atualizado \nID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não foi encontrado.");
        }
    }

    // Deletar Ninja (DELETE)
    // O usuário vai passar o ID pela url
    @DeleteMapping("/deletarID/{id}")
    public ResponseEntity<String> DeletarNinjaPorId(@PathVariable Long id) {
        if (ninja_service.ListarNinjasPorId(id) != null){
            ninja_service.DeletarNinja(id);
            return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado.");
        }
    }

}
