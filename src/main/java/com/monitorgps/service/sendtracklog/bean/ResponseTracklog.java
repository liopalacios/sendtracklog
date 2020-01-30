package com.monitorgps.service.sendtracklog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTracklog {
    private String Placa;
    private Date Fecha_Hora;
}
