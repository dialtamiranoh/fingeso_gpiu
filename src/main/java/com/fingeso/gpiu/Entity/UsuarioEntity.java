package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "correo", length = 200)
    private String correo;
}



