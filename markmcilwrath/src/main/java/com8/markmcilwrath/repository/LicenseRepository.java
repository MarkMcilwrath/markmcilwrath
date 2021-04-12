package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface LicenseRepository extends CrudRepository<LicenseEntity, Long>
{
    LicenseEntity findByLicenseKey (String licenseKey);

    @Transactional
    void deleteByLicenseKey(String LicenseKey);
}
