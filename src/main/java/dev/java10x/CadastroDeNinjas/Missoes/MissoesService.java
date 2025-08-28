package dev.java10x.CadastroDeNinjas.Missoes;

import java.util.List;
import java.util.Optional;

public class MissoesService {

    private MissoesRepository missoes_repository;

    public MissoesService(MissoesRepository missoes_repository) {
        this.missoes_repository = missoes_repository;
    }

    public MissoesModel CriarMissao(MissoesModel missao){
        return missoes_repository.save(missao);
    }

    public List<MissoesModel> ListarMissoes(){
        return missoes_repository.findAll();
    }

    public MissoesModel ListarMissaoPorId(Long id){
        Optional<MissoesModel> missao_encontrada = missoes_repository.findById(id);
        return missao_encontrada.orElse(null);
    }

    public void DeletarMissao(Long id){
        missoes_repository.deleteById(id);
    }
}
