package com.fingeso.gpiu.Repository;

import com.fingeso.gpiu.Entity.HorarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer> {

    List<HorarioEntity> findByCursoIdCurso(Integer idCurso);

    List<HorarioEntity> findBySalaIdSala(Integer idSala);

    List<HorarioEntity> findByDia(String dia);

    @Query("SELECT h FROM HorarioEntity h " +
            "JOIN h.curso c " +
            "JOIN c.alumnos a " +
            "WHERE a.idUsuario = :idAlumno")
    List<HorarioEntity> findByAlumnoId(@Param("idAlumno") Integer idAlumno);

    @Query("SELECT h FROM HorarioEntity h " +
            "JOIN h.curso c " +
            "JOIN c.profesores p " +
            "WHERE p.idUsuario = :idProfesor")
    List<HorarioEntity> findByProfesorId(@Param("idProfesor") Integer idProfesor);
}