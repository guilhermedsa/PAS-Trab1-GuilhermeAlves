package com.pucrs.galves001.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pucrs.galves001.demo.models.*;
import com.pucrs.galves001.demo.repo.*;
import com.pucrs.galves001.demo.services.*;


@RestController
@RequestMapping("/acmespiele")
public class JogoController {

    @Autowired private ClienteRepository clienteRepo;
    @Autowired private JogoRepository jogoRepo;
    @Autowired private ContratoRepository contratoRepo;
    @Autowired private UsoRepository usoRepo;
    @Autowired private JogoServices jogoService;

    // 1. Consultar todos os clientes
    @GetMapping("/listaclientes")
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    // 2. Consultar todos os jogos
    @GetMapping("/listajogos")
    public List<Jogo> listarJogos() {
        return jogoRepo.findAll();
    }

    // 3. Consultar todos os contratos
    @GetMapping("/listacontratos")
    public List<Contrato> listarContratos() {
        return contratoRepo.findAll();
    }

    // 4. Consultar jogos de uma situação
    @GetMapping("/consultarjogossituacao/{situacao}")
    public List<Jogo> consultarJogosSituacao(@PathVariable String situacao) {
        return jogoRepo.findBySituacao(SituacaoJogo.valueOf(situacao.toUpperCase()));
    }

    // 5. Cadastrar um novo contrato
    @PostMapping("/cadastro/cadcontrato")
    public boolean cadastrarContrato(@RequestBody Map<String, Object> payload) {
        try {
            Contrato c = new Contrato();
            // Lógica simplificada de extração. Num cenário real, criaríamos um DTO.
            c.setPeriodo((int) payload.get("periodo"));
            
            Cliente cliente = clienteRepo.findById((String) payload.get("cpf")).orElseThrow();
            Jogo jogo = jogoRepo.findById((int) payload.get("codigo")).orElseThrow();
            
            c.setCliente(cliente);
            c.setJogo(jogo);
            
            // Atualiza status do jogo para contratado
            jogo.setSituacao(SituacaoJogo.CONTRATADO);
            jogoRepo.save(jogo);
            
            contratoRepo.save(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 6. Cadastrar um novo uso
    @PostMapping("/cadastro/caduso")
    public boolean cadastrarUso(@RequestBody Map<String, Object> payload) {
        try {
            Uso u = new Uso();
            u.setHorarioInicio((int) payload.get("horarioInicio"));
            u.setHorarioFim((int) payload.get("horarioFim"));
            
            Contrato c = contratoRepo.findById((int) payload.get("id")).orElseThrow();
            u.setContrato(c);
            
            usoRepo.save(u);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 7. Calcular total de um contrato
    @GetMapping("/consultatotalcontrato")
    public Map<String, Double> consultarTotalContrato(@RequestParam int id) {
        return Map.of("valor_total", jogoService.calcularValorContrato(id));
    }

    // 8. Calcular total de um cliente
    @GetMapping("/consultatotalcliente")
    public Map<String, Double> consultarTotalCliente(@RequestParam String cpf) {
        return Map.of("valor_total_cobranca", jogoService.calcularCobrancaCliente(cpf));
    }

    // 9. Atualizar situação do jogo
    @PutMapping("/cadastro/atualizajogo/{codigo}/situacao/{status}")
    public Jogo atualizarJogo(@PathVariable int codigo, @PathVariable String status) {
        Jogo jogo = jogoRepo.findById(codigo).orElseThrow();
        jogo.setSituacao(SituacaoJogo.valueOf(status.toUpperCase()));
        return jogoRepo.save(jogo);
    }

    // 10. Cancelar contrato logicamente
    @DeleteMapping("/cadastro/cancelacontrato")
    public boolean cancelarContrato(@RequestBody Map<String, Integer> payload) {
        Optional<Contrato> opt = contratoRepo.findById(payload.get("id"));
        if (opt.isPresent()) {
            Contrato c = opt.get();
            c.setAtivo(false); 
            contratoRepo.save(c);
            return true;
        }
        return false;
    }
}