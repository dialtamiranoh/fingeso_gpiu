package com.fingeso.gpiu.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "piu")
public class PiuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_piu")
    private Integer idPiu;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "tipo_ubicacion", length = 20)
    private String tipoUbicacion;  // "INTERIOR" o "EXTERIOR"

    @Column(name = "nombre_edificio", length = 100)
    private String nombreEdificio;  // null si es EXTERIOR

    @Column(name = "piso")
    private Integer piso;  // null si es EXTERIOR

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;  // "ACTIVO", "INACTIVO", "MANTENIMIENTO"
}