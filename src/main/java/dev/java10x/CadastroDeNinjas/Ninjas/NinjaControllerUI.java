package dev.java10x.CadastroDeNinjas.Ninjas;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninja_service;
    private final MissoesService missoes_service;

    public NinjaControllerUI(NinjaService ninja_service, MissoesService missoes_service) {
        this.ninja_service = ninja_service;
        this.missoes_service = missoes_service;
    }

    @GetMapping("/listar")
    public String ListarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninja_service.ListarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "ListarNinjas"; //tem que retornar o nome da página que renderiza
    }

    @GetMapping("/deletar/{id}")
    public String DeletarNinjaPorId(@PathVariable Long id) {
        ninja_service.DeletarNinja(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String ListarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja =  ninja_service.ListarNinjasPorId(id);
        if (ninja !=null) {
            model.addAttribute("ninja", ninja);
            return "DetalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "ListarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String MostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        List<MissoesDTO> todasAsMissoes = missoes_service.ListarMissoes();
        model.addAttribute("todasAsMissoes", todasAsMissoes);
        return "AdicionarNinja";
    }

    @PostMapping("/salvar")
    public String SalvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninja_service.CriarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    @PostMapping("/alterar/{id}")
    public String AtualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninja_service.AtualizarNinja(id, ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String MostrarFormularioEditarNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninja_service.ListarNinjasPorId(id);
        if (ninja != null) {
            List<MissoesDTO> missoes = missoes_service.ListarMissoes(); // 🔹 busca todas as missões
            model.addAttribute("ninja", ninja);
            model.addAttribute("missoes", missoes);
            return "AlterarNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "redirect:/ninjas/ui/listar";
        }
    }

}
