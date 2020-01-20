package com.monitorgps.service.sendtracklog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTracklog {
    private String placa;
    private String fechaEvento;
    private String horaEvento;
    private String latitud;
    private String longitud;
    private int direccion;
    private int velocidad;
    private String evento;
}
