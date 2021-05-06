package com8.markmcilwrath.service;


import com8.markmcilwrath.domain.entity.*;
import com8.markmcilwrath.repository.LicenseArchiveRepository;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import com8.markmcilwrath.repository.LicenseRepository;

import org.springframework.stereotype.Service;



@Service
public class LicenseArchiveService {

    private LicenseArchiveRepository licenseArchiveRepository;
    private LicenseRepository licenseRepository;

    public LicenseArchiveService(LicenseArchiveRepository licenseArchiveRepository,
                                 LicenseRepository licenseRepository)
    {
        this.licenseArchiveRepository = licenseArchiveRepository;
        this.licenseRepository=licenseRepository;
    }

    public void archive (String licenseKey)
    {
        LicenseEntity licenseEntity = licenseRepository.findByLicenseKey(licenseKey);

        LicenseArchiveEntity archiveEntity = new LicenseArchiveEntity(licenseKey,
                licenseEntity.getPurchaseDate(), licenseEntity.getExpiryDate(),
                licenseEntity.getSoftwareEntity().getId(),
                licenseEntity.getSoftwareEntity().getName(), licenseEntity.getSoftwareEntity().getVersion());

        LicenseArchiveEntity archivedEntity= licenseArchiveRepository.save(archiveEntity);
    }
}