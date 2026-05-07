package com.pucrs.galves001.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Uso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    //para facilitar o cálculo
    private int horarioInicio;
    private int horarioFim;
    
    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}