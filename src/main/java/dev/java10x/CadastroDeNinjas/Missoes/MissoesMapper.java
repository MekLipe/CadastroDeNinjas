package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaMapper;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissoesMapper {

    private NinjaMapper ninja_mapper;

    public MissoesMapper(NinjaMapper ninja_mapper) {
        this.ninja_mapper = ninja_mapper;
    }

    public MissoesModel map(MissoesDTO missoesDTO){

        MissoesModel missoes_model = new MissoesModel();

        missoes_model.setId(missoesDTO.getId());
        missoes_model.setNome(missoesDTO.getNome());
        missoes_model.setDificuldade(missoesDTO.getDificuldade());
        if (missoesDTO.getNinjas() != null){
            List<NinjaModel> ninjas = missoesDTO.getNinjas().stream()
                    .map(ninja_mapper::map)
                    .toList();
            missoes_model.setNinjas(ninjas);
        }
        return missoes_model;
    }

    public MissoesDTO map(MissoesModel missoes_model){

        MissoesDTO missoesDTO = new MissoesDTO();

        missoesDTO.setId(missoes_model.getId());
        missoesDTO.setNome(missoes_model.getNome());
        missoesDTO.setDificuldade(missoes_model.getDificuldade());

        if (missoes_model.getNinjas() != null){
            List<NinjaDTO> ninjas = missoes_model.getNinjas().stream()
                    .map(ninja_mapper::map)
                    .toList();
            missoesDTO.setNinjas(ninjas);
        }
        return missoesDTO;
    }
}
