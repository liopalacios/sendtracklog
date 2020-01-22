package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_recorrido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecorridoEntity implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tb_recorrido_id_recorrido_seq")
    @Column(name="id_recorrido")
    private Long id;

    @Column(name = "coord_x")
    private String latitud;

    @Column(name = "coord_y")
    private String longitud;

    @Column(name = "vel_recorrido")
    private Double velocidad;

    @Column(name = "grados")
    private Double orientacion;

}
