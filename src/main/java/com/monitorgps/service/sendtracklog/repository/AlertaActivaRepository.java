package com.monitorgps.service.sendtracklog.repository;

import com.monitorgps.service.sendtracklog.model.AlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaActivaRepository extends JpaRepository<AlertaEntity,Long> {


    @Query(value = "select v.placa as placa, TO_CHAR(a.fecreg,'YYYY-MM-DD') as fechaEvento, to_char(a.fecreg, 'HH24:MI:SS') as horaEvento, " +
            "r.latitud, r.longitud, " +
            "case a.alerta when 3 then 540 when 4 then 501 when 34 then 500 when 55 then 170 end as evento, " +
            "r.velocidad, r.orientacion " +
            "from AlertaActivaEntity  a " +
            "left join a.recorrido r " +
            "left join a.vehiculo v " +
            "where a.alerta in(3,4,34,55) and TO_CHAR(a.fecreg,'YYYY-MM-DD HH24:MI')= :format")
    List<Object[]> getListEventsByMinute(@Param("format") String format);



}
