package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.LicenseArchiveEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface LicenseArchiveRepository extends CrudRepository<LicenseArchiveEntity, Long>
{
    LicenseArchiveEntity findByLicenseKey (String licenseKey);
}
