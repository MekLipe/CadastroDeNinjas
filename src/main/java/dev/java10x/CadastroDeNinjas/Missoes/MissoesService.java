package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoes_repository;
    private MissoesMapper missoes_mapper;

    public MissoesService(MissoesRepository missoes_repository, MissoesMapper missoes_mapper) {
        this.missoes_repository = missoes_repository;
        this.missoes_mapper = missoes_mapper;
    }

    public MissoesDTO CriarMissao(MissoesDTO missaoDTO){
        MissoesModel missao = missoes_mapper.map(missaoDTO);
        missao = missoes_repository.save(missao);
        return missoes_mapper.map(missao);
    }

    public List<MissoesDTO> ListarMissoes(){
        List<MissoesModel> missoes = missoes_repository.findAll();
        return missoes.stream()
                .map(missoes_mapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO ListarMissaoPorId(Long id){
        Optional<MissoesModel> missao_encontrada = missoes_repository.findById(id);
        return missao_encontrada.map(missoes_mapper::map).orElse(null);
    }

    public MissoesDTO AtualizarMissao(Long id, MissoesDTO misssaoDTO){
        Optional<MissoesModel> missao_existente = missoes_repository.findById(id);
        if (missao_existente.isPresent()){
            MissoesModel missao_atualizada = missoes_mapper.map(misssaoDTO);
            missao_atualizada.setId(id);
            MissoesModel missao_salva = missoes_repository.save(missao_atualizada);
            return missoes_mapper.map(missao_salva);
        }
        return null;
    }

    public void DeletarMissao(Long id){
        missoes_repository.deleteById(id);
    }
}
