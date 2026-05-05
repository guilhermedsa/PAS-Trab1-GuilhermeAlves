package com.pucrs.galves001.demo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;



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
    private SituacaoJogo situacao = SituacaoJogo.DISPONIVEL; // Todo jogo inicia como disponível
}