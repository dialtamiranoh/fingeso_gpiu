package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "informacion_general")
@Data
public class InformacionGeneralEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion")
    private Long idInformacion;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "orden_visualizacion")
    private Integer ordenVisualizacion;
}