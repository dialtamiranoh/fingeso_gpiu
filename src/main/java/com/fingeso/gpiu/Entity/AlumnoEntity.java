package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "alumno")
public class AlumnoEntity extends UsuarioEntity {

    @Column(name = "carrera", length = 200)
    private String carrera;

    @Column(name = "estado_matricula", length = 50)
    private String estadoMatricula;

    @ManyToMany
    @JoinTable(
            name = "alumno_curso",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<CursoEntity> cursos;

}
