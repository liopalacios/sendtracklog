package com.monitorgps.service.sendtracklog.repository;

import com.monitorgps.service.sendtracklog.model.AlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<AlertaEntity,Long> {
}
