package com.pucrs.galves001.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.pucrs.galves001.demo.models.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    List<Contrato> findByClienteCpf(String cpf);
}
