package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    public MissoesController(MissoesService missoes_service) {
        this.missoes_service = missoes_service;
    }

    private MissoesService missoes_service;

    // GET -- Mandar uma requisao para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesModel> ListarMissoes() {
        return missoes_service.ListarMissoes();
    }

    @GetMapping("/listarID/{id}")
    public MissoesModel ListarMissaoPorId(@PathVariable Long id){
        return missoes_service.ListarMissaoPorId(id);
    }

    // Post -- Mandar uma requisao para criar as missoes
    @PostMapping("/criar")
    public void CriarMissao(@RequestBody MissoesModel missao) {
        missoes_service.CriarMissao(missao);
    }

    // PUT -- Mandar uma requisao para alterar as missoes
    @PutMapping("/alterar")
    public String AlterarMissao() {
        return "Missao alterada com sucesso";
    }

    // Delete -- Mandar uma requisao para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void DeletarMissao(@PathVariable Long id) {
        missoes_service.DeletarMissao(id);
    }


}