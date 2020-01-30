package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @OneToOne
    @JoinColumn(name="id_vehiculo")
    private VehiculoEntity vehiculo;

    @Column(name = "vel_recorrido")
    private Double velocidad;

    @Column(name = "grados")
    private Double orientacion;

    @Column(name = "fec_enviado_tracklog")
    private Date fechaenviado;

    @Column(name="fec_recorrido")
    private Date fecreg;

}
