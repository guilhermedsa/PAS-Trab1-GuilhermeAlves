package com.pucrs.galves001.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Jogo {
    @Id
    private int codigo;
    private String nome;
    private int ano;
    private double valorMinuto;
    
    @ManyToOne
    @JoinColumn(name = "categoria_num")
    private Categoria categoria;
    
    @Enumerated(EnumType.STRING)
    private SituacaoJogo situacao = SituacaoJogo.DISPONIVEL; 
}