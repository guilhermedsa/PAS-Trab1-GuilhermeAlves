package com.pucrs.galves001.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pucrs.galves001.demo.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
