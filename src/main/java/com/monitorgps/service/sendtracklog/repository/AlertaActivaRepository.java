package com.monitorgps.service.sendtracklog.repository;

import com.monitorgps.service.sendtracklog.model.AlertaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface AlertaActivaRepository extends JpaRepository<AlertaEntity,Long> {


    @Query(value = "select v.placa as placa, TO_CHAR(r.fecreg,'YYYY-MM-DD') as fechaEvento, to_char(r.fecreg, 'HH24:MI:SS') as horaEvento, " +
            "r.latitud, r.longitud, " +
            "case a.alerta when 3 then 540 when 4 then 501 when 34 then 500 when 55 then 170 else 2 end  as evento, " +
            "r.velocidad, r.orientacion " +
            "from AlertaActivaEntity  a " +
            "left join a.recorrido r " +
            "left join a.vehiculo v " +
            "where r.fecreg > :s and r.fecreg < :format and v.id in(5417,4941,4904,4042,4053,4041,4040,4043,4022) and r.fechaenviado = NULL ")
    List<Object[]> getListEventsByMinute(@Param("format") Date format,@Param("s") Date s);

    @Transactional
    @Modifying
    @Query(value = "update RecorridoEntity a set a.fechaenviado  = :hoy where  TO_CHAR(a.fecreg,'YYYY-MM-DD HH24:MI') = :fecha and a.vehiculo" +
            " in(select v.id from VehiculoEntity v where v.placa = :placa) ")
    void getUpdateByMinute(@Param("hoy")Date hoy,@Param("fecha")String fecha,@Param("placa")String placa );


}
