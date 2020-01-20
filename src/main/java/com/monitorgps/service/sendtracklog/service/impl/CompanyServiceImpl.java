package com.monitorgps.service.sendtracklog.service.impl;

import com.monitorgps.service.sendtracklog.model.CompanyEntity;
import com.monitorgps.service.sendtracklog.repository.CompanyRepository;
import com.monitorgps.service.sendtracklog.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<CompanyEntity> listCompany() {
        return companyRepository.findAll();
    }
}
