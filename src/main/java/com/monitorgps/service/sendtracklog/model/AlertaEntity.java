package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlertaEntity implements Serializable {
    private static final long serialVersionUID = 3L;

    @Column(name = "id_alerta")
    private Long id;

    @Column(name = "des_alerta")
    private String descripcion;
}
