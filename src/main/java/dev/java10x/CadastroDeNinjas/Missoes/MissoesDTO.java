package dev.java10x.CadastroDeNinjas.Missoes;


import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissoesDTO {
    private Long id;
    private String nome;
    private char dificuldade;
    private List<NinjaDTO> ninjas;
}
