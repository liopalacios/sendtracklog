package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "tb_vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehiculoEntity implements Serializable {
    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tb_vehiculo_id_vehiculo_seq")
    @Column(name="id_vehiculo")
    private Long id;

    @Column(name = "num_placa")
    private String placa;
}
