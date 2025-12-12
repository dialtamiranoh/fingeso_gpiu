package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Integer> {
}