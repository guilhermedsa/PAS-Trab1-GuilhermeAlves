package com.pucrs.galves001.demo;

import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pucrs.galves001.demo.models.*;
import com.pucrs.galves001.demo.repo.*;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoriaRepository categoriaRepo;
    private final ClienteRepository clienteRepo;
    private final JogoRepository jogoRepo;
    private final ContratoRepository contratoRepo;

    public DataSeeder(CategoriaRepository categoriaRepo, ClienteRepository clienteRepo,
                      JogoRepository jogoRepo, ContratoRepository contratoRepo) {
        this.categoriaRepo = categoriaRepo;
        this.clienteRepo = clienteRepo;
        this.jogoRepo = jogoRepo;
        this.contratoRepo = contratoRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepo.count() > 0) return; // Evita duplicar se rodar de novo

        Categoria cat = new Categoria();
        cat.setNome("Geral");
        cat.setValorMinimo(10.0);
        categoriaRepo.save(cat);

        // 5 Jogos
        Jogo j1 = new Jogo(); j1.setCodigo(1); j1.setNome("Jogo A"); j1.setAno(2023); j1.setValorMinuto(1.0); j1.setCategoria(cat); j1.setSituacao(SituacaoJogo.CONTRATADO);
        Jogo j2 = new Jogo(); j2.setCodigo(2); j2.setNome("Jogo B"); j2.setAno(2024); j2.setValorMinuto(1.0); j2.setCategoria(cat); j2.setSituacao(SituacaoJogo.CONTRATADO);
        Jogo j3 = new Jogo(); j3.setCodigo(3); j3.setNome("Jogo C"); j3.setAno(2025); j3.setValorMinuto(1.0); j3.setCategoria(cat); j3.setSituacao(SituacaoJogo.CONTRATADO);
        Jogo j4 = new Jogo(); j4.setCodigo(4); j4.setNome("Jogo D"); j4.setAno(2026); j4.setValorMinuto(1.0); j4.setCategoria(cat); j4.setSituacao(SituacaoJogo.CONTRATADO);
        Jogo j5 = new Jogo(); j5.setCodigo(5); j5.setNome("Jogo E"); j5.setAno(2026); j5.setValorMinuto(1.0); j5.setCategoria(cat); j5.setSituacao(SituacaoJogo.DISPONIVEL);
        jogoRepo.saveAll(List.of(j1, j2, j3, j4, j5));

        // 3 Clientes
        Cliente c1 = new Cliente(); c1.setCpf("111"); c1.setNome("Cli 1"); c1.setUsername("c1"); c1.setPassword("123"); c1.setEmail("cli@email.com");
        Cliente c2 = new Cliente(); c2.setCpf("222"); c2.setNome("Cli 2"); c2.setUsername("c2"); c2.setPassword("123"); c2.setEmail("cli2@email.com");
        Cliente c3 = new Cliente(); c3.setCpf("333"); c3.setNome("Cli 3"); c3.setUsername("c3"); c3.setPassword("123"); c3.setEmail("cli3@email.com");
        clienteRepo.saveAll(List.of(c1, c2, c3));

        // 4 Contratos (2 para o Cli 1, 2 para o Cli 2)
        Contrato con1 = new Contrato(); con1.setData(new Date()); con1.setPeriodo(10); con1.setCliente(c1); con1.setJogo(j1);
        Contrato con2 = new Contrato(); con2.setData(new Date()); con2.setPeriodo(10); con2.setCliente(c1); con2.setJogo(j2);
        Contrato con3 = new Contrato(); con3.setData(new Date()); con3.setPeriodo(10); con3.setCliente(c2); con3.setJogo(j3);
        Contrato con4 = new Contrato(); con4.setData(new Date()); con4.setPeriodo(10); con4.setCliente(c2); con4.setJogo(j4);
        contratoRepo.saveAll(List.of(con1, con2, con3, con4));
    }
}