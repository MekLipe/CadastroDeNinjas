package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private NinjaRepository ninja_repository;

    public NinjaService(NinjaRepository ninja_repository) {
        this.ninja_repository = ninja_repository;
    }

    // Listar todos os ninjas
    public List<NinjaModel> ListarNinjas(){
        return ninja_repository.findAll();
    }
}
