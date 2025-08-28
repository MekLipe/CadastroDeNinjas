package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninja_repository;

    public NinjaService(NinjaRepository ninja_repository) {
        this.ninja_repository = ninja_repository;
    }

    //Criar novo ninja (CREATE)
    public NinjaModel CriarNinja(NinjaModel ninja){
        return ninja_repository.save(ninja);
    }

    // Listar todos os ninjas (READ)
    public List<NinjaModel> ListarNinjas(){
        return ninja_repository.findAll();
    }

    // Listar por ID (READ)
    public NinjaModel ListarNinjasPorId(Long id){
        Optional<NinjaModel> ninja_encontrado = ninja_repository.findById(id);
        return ninja_encontrado.orElse(null);
    }

    // Atualizar por ID (UPDATE)
    public NinjaModel AtualizarNinja(Long id, NinjaModel ninja_atualizado){
        if (ninja_repository.existsById(id)){
            ninja_atualizado.setId(id);
            return ninja_repository.save(ninja_atualizado);
        }
        return null;
    }

    // Deletar por ID (DELETE)
    // Tem que ser um Procedimento/Void, pois não é retornado nada
    public void DeletarNinja(Long id){
        ninja_repository.deleteById(id);
    }
}
