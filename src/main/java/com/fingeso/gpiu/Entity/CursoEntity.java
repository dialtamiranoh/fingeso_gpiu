package com.fingeso.gpiu.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "cursos")
    private List<AlumnoEntity> alumnos;

    @JsonIgnore
    @ManyToMany(mappedBy = "cursos")
    private List<ProfesorEntity> profesores;

    // Getters y Setters
    public Integer getIdCurso() { return idCurso; }
    public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<AlumnoEntity> getAlumnos() { return alumnos; }
    public void setAlumnos(List<AlumnoEntity> alumnos) { this.alumnos = alumnos; }

    public List<ProfesorEntity> getProfesores() { return profesores; }
    public void setProfesores(List<ProfesorEntity> profesores) { this.profesores = profesores; }
}