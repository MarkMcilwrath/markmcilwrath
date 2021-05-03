package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface LicenseRepository extends CrudRepository<LicenseEntity, Long>
{
    LicenseEntity findByLicenseKey (String licenseKey);

    Iterable<LicenseEntity> findBySoftwareEntity (SoftwareEntity softwareEntity);

    @Transactional
    void deleteByLicenseKey(String LicenseKey);
}
