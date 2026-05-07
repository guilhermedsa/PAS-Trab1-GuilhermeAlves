package com.pucrs.galves001.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.pucrs.galves001.demo.models.Jogo;
import com.pucrs.galves001.demo.models.SituacaoJogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    List<Jogo> findBySituacao(SituacaoJogo situacao);
}
