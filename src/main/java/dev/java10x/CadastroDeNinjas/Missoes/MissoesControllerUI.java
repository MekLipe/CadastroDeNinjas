package dev.java10x.CadastroDeNinjas.Missoes;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoes_service;
    public MissoesControllerUI(MissoesService missoes_service) {
        this.missoes_service = missoes_service;
    }

    @GetMapping("/listar")
    public String ListarMissoes(Model model) {
        List<MissoesDTO> lista_missoes = missoes_service.ListarMissoes();
        model.addAttribute("missoes", lista_missoes);
        return "Missoes/ListarMissoes";
    }

    // GET -- Mandar uma requisição para listar uma missao por id
    @GetMapping("/listarID/{id}")
    public String ListarMissaoPorId(@PathVariable Long id, Model model){
        MissoesDTO missao = missoes_service.ListarMissaoPorId(id);
        if (missao != null)
        {
            model.addAttribute("missao", missao);
            return "Missoes/DetalhesMissao";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "Missoes/ListarMissao";
        }
    }

    @GetMapping("/adicionar")
    public String MostrarFormularioAdicionarMissao(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "Missoes/AdicionarMissao";
    }

    @PostMapping("/salvar")
    public String SalvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoes_service.CriarMissao(missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @PostMapping("/deletar/{id}")
    public String DeletarMissaoPorId(@PathVariable Long id){
        missoes_service.DeletarMissao(id);
        return "redirect:missoes/ui/listar";
    }

    @PostMapping("/alterar/{id}")
    public String AtualizarMissao(@PathVariable Long id, @ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
        missoes_service.AtualizarMissao(id, missao);
        redirectAttributes.addFlashAttribute("mensagem", "Missao atualizada com sucesso!");
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String MostrarFormularioEditarMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoes_service.ListarMissaoPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "Missoes/AlterarMissao";
        } else {
            model.addAttribute("mensagem", "Missão não encontrada");
            return "redirect:/missoes/ui/listar";
        }
    }
}
