package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "sala")
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Integer idSala;

    @Column(name = "codigo", length = 50, unique = true)
    private String codigo;

    @Column(name = "ubicacion", length = 200)
    private String ubicacion;

    @OneToMany(mappedBy = "sala")
    private List<HorarioEntity> horarios;
}