package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    Optional<CursoEntity> findByCodigo(String codigo);
}