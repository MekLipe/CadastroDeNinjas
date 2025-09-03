package dev.java10x.CadastroDeNinjas.Missoes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    public MissoesController(MissoesService missoes_service) {
        this.missoes_service = missoes_service;
    }

    private MissoesService missoes_service;

    // GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesDTO> ListarMissoes() {
        return missoes_service.ListarMissoes();
    }

    // GET -- Madanr uma requisição para listar uma missao por id
    @GetMapping("/listarID/{id}")
    public MissoesDTO ListarMissaoPorId(@PathVariable Long id){
        return missoes_service.ListarMissaoPorId(id);
    }

    // Post -- Mandar uma requisiçao para criar as missoes
    @PostMapping("/criar")
    public void CriarMissao(@RequestBody MissoesDTO missao) {
        missoes_service.CriarMissao(missao);
    }

    // PUT -- Mandar uma requisiçao para alterar as missoes
    @PutMapping("/alterarID/{id}")
    public MissoesDTO AlterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao_atualizada) {
        return missoes_service.AtualizarMissao(id, missao_atualizada);
    }

    // Delete -- Mandar uma requisiçao para deletar as missoes
    @DeleteMapping("/deletarID/{id}")
    public void DeletarMissao(@PathVariable Long id) {
        missoes_service.DeletarMissao(id);
    }
}