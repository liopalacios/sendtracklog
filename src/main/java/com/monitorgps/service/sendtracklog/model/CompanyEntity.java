package com.monitorgps.service.sendtracklog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
@Entity
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod_company")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "company_seq")
    private Integer id;

    @Column(name = "bucket", length = 150, nullable = false)
    private String bucket;

    @Column(name = "direccion", length = 500)
    private String direccion;
}
