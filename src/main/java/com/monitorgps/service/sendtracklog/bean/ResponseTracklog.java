package com.monitorgps.service.sendtracklog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTracklog {
    private String Placa;
    private String Fecha_Hora;
}
