package com.fingeso.gpiu.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesor")
public class ProfesorEntity extends UsuarioEntity {

    @Column(name = "departamento", length = 200)
    private String departamento;

    @Column(name = "especialidad", length = 200)
    private String especialidad;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "profesor_curso",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<CursoEntity> cursos;

    // Getters y Setters
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public List<CursoEntity> getCursos() { return cursos; }
    public void setCursos(List<CursoEntity> cursos) { this.cursos = cursos; }
}