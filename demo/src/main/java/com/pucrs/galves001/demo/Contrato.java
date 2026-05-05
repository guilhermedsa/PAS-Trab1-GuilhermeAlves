package com.pucrs.galves001.demo;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date data;
    private int periodo; 
    
    private boolean ativo = true; 
    
    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "jogo_codigo")
    private Jogo jogo;
    
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Uso> usos;
};