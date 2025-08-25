package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // GET -- Mandar uma requisao para mostrar as missoes
    @GetMapping("/listar")
    public String ListarMissoes() {
        return "Missoes listadas com sucesso";
    }

    // Post -- Mandar uma requisao para criar as missoes
    @PostMapping("/criar")
    public String CriarMissao() {
        return "Missao criada com sucesso";
    }

    // PUT -- Mandar uma requisao para alterar as missoes
    @PutMapping("/alterar")
    public String AlterarMissao() {
        return "Missao alterada com sucesso";
    }

    // Delete -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar")
    public String DeletarMissao() {
        return "Missao deletada com sucesso";
    }


}