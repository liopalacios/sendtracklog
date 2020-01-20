package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_alerta_activa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaActivaEntity implements Serializable {
    private static final long serialVersionUID = 2L;
    private String nombre;
}
