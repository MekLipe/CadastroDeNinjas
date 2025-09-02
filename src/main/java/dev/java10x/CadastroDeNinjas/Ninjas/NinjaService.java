package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninja_repository;
    private NinjaMapper ninja_mapper;

    public NinjaService(NinjaRepository ninja_repository, NinjaMapper ninja_mapper) {
        this.ninja_repository = ninja_repository;
        this.ninja_mapper = ninja_mapper;
    }

    //Criar novo ninja (CREATE)
    public NinjaDTO CriarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninja_mapper.map(ninjaDTO);
        ninja = ninja_repository.save(ninja);
        return ninja_mapper.map(ninja);
    }

    // Listar todos os ninjas (READ)
    public List<NinjaDTO> ListarNinjas(){
        List<NinjaModel> ninjas = ninja_repository.findAll();
        return ninjas.stream()
                .map(ninja_mapper::map)
                .collect(Collectors.toList());
    }

    // Listar por ID (READ)
    public NinjaDTO ListarNinjasPorId(Long id){
        Optional<NinjaModel> ninja_encontrado = ninja_repository.findById(id);
        return ninja_encontrado.map(ninja_mapper::map).orElse(null);
    }

    // Atualizar por ID (UPDATE)
    public NinjaDTO AtualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninja_existente = ninja_repository.findById(id);
        if (ninja_existente.isPresent()){
            NinjaModel ninja_atualizado = ninja_mapper.map(ninjaDTO);
            ninja_atualizado.setId(id);
            NinjaModel ninja_salvo = ninja_repository.save(ninja_atualizado);
            return ninja_mapper.map(ninja_salvo);
        }
        return null;
    }

    // Deletar por ID (DELETE)
    // Tem que ser um Procedimento/Void, pois não é retornado nada
    public void DeletarNinja(Long id){
        ninja_repository.deleteById(id);
    }
}
