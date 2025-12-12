package com.fingeso.gpiu.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "sala")
    private List<HorarioEntity> horarios;

    // Getters y Setters
    public Integer getIdSala() { return idSala; }
    public void setIdSala(Integer idSala) { this.idSala = idSala; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<HorarioEntity> getHorarios() { return horarios; }
    public void setHorarios(List<HorarioEntity> horarios) { this.horarios = horarios; }
}