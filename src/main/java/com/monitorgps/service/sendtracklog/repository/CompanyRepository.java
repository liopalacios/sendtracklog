package com.monitorgps.service.sendtracklog.repository;

import com.monitorgps.service.sendtracklog.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {
}
