package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "curso")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "codigo", length = 50, unique = true)
    private String codigo;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "cursos")
    private List<AlumnoEntity> alumnos;

    @ManyToMany(mappedBy = "cursos")
    private List<ProfesorEntity> profesores;
}

