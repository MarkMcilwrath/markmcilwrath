package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import com8.markmcilwrath.repository.LicenseRepository;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class LicenseService {

    private LicenseRepository licenseRepository;
    private LicenseAssignmentRepository licenseAssignmentRepository;
    private SoftwareService softwareService;

    public LicenseService (LicenseRepository licenseRepository, SoftwareService softwareService,
                           LicenseAssignmentRepository licenseAssignmentRepository)
    {
        this.licenseRepository = licenseRepository;
        this.softwareService = softwareService;
        this.licenseAssignmentRepository = licenseAssignmentRepository;
    }

    public License save (String licenseKey , LocalDate purchaseDate, LocalDate expiryDate, String softwareID)
    {
        SoftwareEntity softwareEntity = null;
        try {
            softwareEntity = getSoftwareEntity(softwareID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        LicenseEntity createEntity = new LicenseEntity(licenseKey, purchaseDate, expiryDate, softwareEntity);
        LicenseEntity createdEntity = licenseRepository.save(createEntity);
        return new License(createdEntity.getLicenseKey(), createdEntity.getPurchaseDate(),createdEntity.getExpiryDate(), softwareEntity.getSoftwareID());
    }

    public License update(License license, String softwareID) throws InvocationTargetException, IllegalAccessException
    {
        SoftwareEntity softwareEntity = null;
        try {
            softwareEntity = getSoftwareEntity(softwareID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        LicenseEntity updateEntity = licenseRepository.findByLicenseKey((license.getLicenseKey()));
        updateEntity.setPurchaseDate(license.getPurchaseDate());
        updateEntity.setExpiryDate(license.getExpiryDate());
        updateEntity.setSoftwareEntity(softwareEntity);
        licenseRepository.save(updateEntity);
        return  new License(updateEntity.getLicenseKey(), updateEntity.getPurchaseDate(), updateEntity.getExpiryDate(), updateEntity.getSoftwareEntity().getSoftwareID());
    }


    public void delete(String licenseKey)
    {
        licenseRepository.deleteByLicenseKey(licenseKey);
    }

    public License getLicense(String licenseKey) throws NotFoundException
    {
        LicenseEntity entity = licenseRepository.findByLicenseKey(licenseKey);

        if (entity == null)
        {
            throw new NotFoundException("License Not Found");
        }
        License license = new License(entity.getLicenseKey(),
                entity.getPurchaseDate(), entity.getExpiryDate(), entity.getSoftwareEntity().getName(), entity.getSoftwareEntity().getSoftwareID(), entity.getSoftwareEntity().getVersion());
        return  license;
    }

    public Set<License> getAllLicense()
    {
        Iterable<LicenseEntity> entityList = licenseRepository.findAll();
        Set<License> licenses = new HashSet<>();

        for (LicenseEntity entity : entityList) {
            License license = new License(entity.getLicenseKey(),
                    entity.getPurchaseDate(), entity.getExpiryDate(), entity.getSoftwareEntity().getName(), entity.getSoftwareEntity().getSoftwareID(), entity.getSoftwareEntity().getVersion());
            licenses.add(license);
        }
        return licenses;
    }

    public Set<License> getAllFreeLicense()
    {
        Iterable<LicenseEntity> entityList = licenseRepository.findAll();
        Set<License> licenses = new HashSet<>();
        Iterable<LicenseAssignmentEntity> assignmentList = licenseAssignmentRepository.findAll();

        for (LicenseEntity entity : entityList)
        {
            boolean assigned = false;
            for (LicenseAssignmentEntity assignmentEntity : assignmentList)
            {
                String key = entity.getLicenseKey();
                String assignKey = assignmentEntity.getLicenseEntity().getLicenseKey();
                if (key == assignKey)
                {
                    assigned = true;
                }
            }
            if (assigned == false)
            {
                License license = new License(entity.getLicenseKey(),
                        entity.getPurchaseDate(), entity.getExpiryDate(), entity.getSoftwareEntity().getName(),
                        entity.getSoftwareEntity().getSoftwareID(), entity.getSoftwareEntity().getVersion());
                licenses.add(license);
            }
        }
        return licenses;
    }

    private SoftwareEntity getSoftwareEntity(String softwareID) throws NotFoundException
    {
        return softwareService.getSoftwareEntity(softwareID);

    }

    public LicenseEntity getLicenseEntity(String licenseKey) throws NotFoundException
    {
        LicenseEntity entity = licenseRepository.findByLicenseKey(licenseKey);
        if (entity == null)
        {
            throw new NotFoundException("Vendor Not found");
        }
        return entity;
    }
}
