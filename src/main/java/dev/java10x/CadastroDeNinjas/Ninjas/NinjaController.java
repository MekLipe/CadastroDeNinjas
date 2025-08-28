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
    // @RequestBody faz com que o usuário envie um dado no qual vai ser serializado e inserido no BD
    @PostMapping("/criar")
    public NinjaModel CriarNinja(@RequestBody NinjaModel ninja) {
        return ninja_service.CriarNinja(ninja);
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> ListarNinjas() {
        return ninja_service.ListarNinjas();
    }

    // Mostrar ninja por id  (READ)
    // {PathVariable} faz com que o usuario mande a variavel que seria o id, pelo caminho/url
    @GetMapping("/listarID/{id}")
    public NinjaModel ListarNinjasPorId(@PathVariable Long id) {
        return ninja_service.ListarNinjasPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaModel AlterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaModel ninja_atulizado) {
        return ninja_service.AtualizarNinja(id, ninja_atulizado);
    }

    // Deletar Ninja (DELETE)
    // O usuário vai passar o ID pela url
    @DeleteMapping("/deletarID/{id}")
    public void DeletarNinjaPorId(@PathVariable Long id) {
        ninja_service.DeletarNinja(id);
    }

}
