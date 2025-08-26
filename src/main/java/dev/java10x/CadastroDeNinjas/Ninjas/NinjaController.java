package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    public NinjaController(NinjaService ninja_service) {
        this.ninja_service = ninja_service;
    }

    private NinjaService ninja_service;

    @GetMapping("/boasvindas")
    public String BoasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String CriarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> ListarNinjas() {
        return ninja_service.ListarNinjas();
    }

    // Mostrar ninja  por id (READ)
    @GetMapping("/listarID")
    public String MostrarTodosOsNinjasPorId() {
        return "Mostrar Ninja por id";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String AlterarNinjaPorId() {
        return "Alterar Ninja por id";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String DeletarNinjaPorId() {
        return "Ninja deletado por id";
    }

}
