package com.pucrs.galves001.demo;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Categoria {
    @Id
    private int num;
    private String nome;
    private double valorMinimo;
};
