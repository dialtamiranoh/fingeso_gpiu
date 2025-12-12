package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {
}