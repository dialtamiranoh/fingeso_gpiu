package com.fingeso.gpiu.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumno")
public class AlumnoEntity extends UsuarioEntity {

    @Column(name = "carrera", length = 200)
    private String carrera;

    @Column(name = "estado_matricula", length = 50)
    private String estadoMatricula;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "alumno_curso",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<CursoEntity> cursos;

    // Getters y Setters
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getEstadoMatricula() { return estadoMatricula; }
    public void setEstadoMatricula(String estadoMatricula) { this.estadoMatricula = estadoMatricula; }

    public List<CursoEntity> getCursos() { return cursos; }
    public void setCursos(List<CursoEntity> cursos) { this.cursos = cursos; }
}