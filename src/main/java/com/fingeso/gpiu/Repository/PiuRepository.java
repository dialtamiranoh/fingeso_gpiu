package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.PiuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiuRepository extends JpaRepository<PiuEntity, Integer> {
    List<PiuEntity> findByEstado(String estado);
}