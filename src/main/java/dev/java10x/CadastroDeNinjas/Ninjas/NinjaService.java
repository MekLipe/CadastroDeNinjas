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

    public NinjaModel CriarNinja(NinjaModel ninja){
        return ninja_repository.save(ninja);
    }

    // Listar todos os ninjas
    public List<NinjaModel> ListarNinjas(){
        return ninja_repository.findAll();
    }

    // Listar por ID
    public NinjaModel ListarNinjasPorId(Long id){
        Optional<NinjaModel> ninja_model = ninja_repository.findById(id);
        return ninja_model.orElse(null);
    }
}
