package com.pucrs.galves001.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    private String cpf;
    private String nome;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private String username;
    private String password;
}