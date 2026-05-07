package com.pucrs.galves001.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pucrs.galves001.demo.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
