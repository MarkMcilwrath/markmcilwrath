package com8.markmcilwrath.service;


import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseArchive;
import com8.markmcilwrath.domain.entity.*;
import com8.markmcilwrath.repository.LicenseArchiveRepository;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import com8.markmcilwrath.repository.LicenseRepository;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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

    public Set<LicenseArchive> getAllArchivedLicense()
    {
        Iterable<LicenseArchiveEntity> entityList = licenseArchiveRepository.findAll();
        Set<LicenseArchive> licenses = new HashSet<>();

        for (LicenseArchiveEntity entity : entityList) {
            LicenseArchive license = new LicenseArchive(
                    entity.getLicenseKey(),
                    entity.getPurchaseDate(),
                    entity.getExpiryDate(),
                    entity.getSoftware_name(),
                    entity.getSoftware_ID(),
                    entity.getSoftware_version());
            licenses.add(license);
        }
        return licenses;
    }
}