package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "profesor")
public class ProfesorEntity extends UsuarioEntity {

    @Column(name = "departamento", length = 200)
    private String departamento;

    @Column(name = "especialidad", length = 200)
    private String especialidad;

    @ManyToMany
    @JoinTable(
            name = "profesor_curso",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<CursoEntity> cursos;

}
