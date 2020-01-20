package com.monitorgps.service.sendtracklog.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertaActivaRepository {
    @Query(value = "select v.num_placa as placa, TO_CHAR(a.fec_registro,'YYYY-MM-DD') as fechaEvento, to_char(a.fec_registro, 'HH24:MI:SS') as horaEvento, " +
            "r.coord_x as latitud, r.coord_y as longitud, " +
            "case a.id_alerta when 3 then 540 when 4 then 501 when 34 then 500 when 55 then 170 end as evento, " +
            "r.vel_recorrido as velocidad, r.direc_recorrido as direccion from tb_alerta_activa a " +
            "inner join tb_alerta al on al.id_alerta=a.id_alerta " +
            "inner join tb_recorrido r on r.id_recorrido=a.id_recorrido " +
            "inner join tb_vehiculo v on v.id_vehiculo=a.id_vehiculo " +
            "where a.id_alerta in(3,4,34,55) and TO_CHAR(a.fec_registro,'YYYY-MM-DD HH24:MI')='2020-01-19 21:57' ")
    List<Object[]> getListEvents();
}
