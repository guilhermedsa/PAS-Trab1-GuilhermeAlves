package com.pucrs.galves001.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.pucrs.galves001.demo.models.*;
import com.pucrs.galves001.demo.repo.ContratoRepository;
import com.pucrs.galves001.demo.repo.JogoRepository;

@Service
public class JogoServices {

    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private JogoRepository jogoRepository;

    public double calcularValorContrato(int contratoId) {
        Contrato contrato = contratoRepository.findById(contratoId).orElseThrow();
        double valorMinimo = contrato.getJogo().getCategoria().getValorMinimo();
        double valorMinuto = contrato.getJogo().getValorMinuto();
        
        int minutosJogados = 0;
        if (contrato.getUsos() != null) {
            for (Uso uso : contrato.getUsos()) {
                minutosJogados += (uso.getHorarioFim() - uso.getHorarioInicio());
            }
        }
        return valorMinimo + (minutosJogados * valorMinuto);
    }

    public double calcularCobrancaCliente(String cpf) {
        List<Contrato> contratos = contratoRepository.findByClienteCpf(cpf);
        double total = 0.0;
        
        for (Contrato c : contratos) {
            if (c.isAtivo()) {
                total += calcularValorContrato(c.getId());
            }
        }
        
        if (total > 500.0) {
            total = total - (total * 0.03); // Desconto de 3% se for > 500
        }
        return total;
    }
}