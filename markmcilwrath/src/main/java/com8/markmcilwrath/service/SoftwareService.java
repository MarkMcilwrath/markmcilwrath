package com8.markmcilwrath.service;


import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.entity.HardwareEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import com8.markmcilwrath.repository.SoftwareRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class SoftwareService {

    private SoftwareRepository softwareRepository;
    private VendorService vendorService;

    public SoftwareService (SoftwareRepository softwareRepository, VendorService vendorService)
    {
        this.softwareRepository = softwareRepository;
        this.vendorService = vendorService;
    }

    public Software save(String name, String version, String vendorID)
    {
        VendorEntity vendorEntity = null;
        try {
            vendorEntity = getVendorEntity(vendorID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        SoftwareEntity createEntity = new SoftwareEntity(UUID.randomUUID().toString(), name, version, vendorEntity);
        SoftwareEntity createdEntity = softwareRepository.save(createEntity);
        return new Software(createdEntity.getSoftwareID(), createdEntity.getName(), createdEntity.getVersion(), createdEntity.getVendorEntity().getVendorId());
    };

    public Software update(Software software, String vendorID) throws InvocationTargetException, IllegalAccessException
    {
        VendorEntity vendorEntity = null;
        try {
            vendorEntity = getVendorEntity(vendorID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        SoftwareEntity updateEntity = softwareRepository.findBySoftwareID((software.getUuid()));
        updateEntity.setName(software.getName());
        updateEntity.setVersion(software.getVersion());
        updateEntity.setVendorEntity(vendorEntity);

        softwareRepository.save(updateEntity);
        return  new Software(updateEntity.getSoftwareID(), updateEntity.getName(), updateEntity.getVersion(), updateEntity.getVendorEntity().getVendorId());
    }

    public void delete(String uuid)
    {
        softwareRepository.deleteBySoftwareID(uuid);
    }


    public Software getSoftware(String uuid) throws NotFoundException
    {
        SoftwareEntity entity = softwareRepository.findBySoftwareID(uuid);
        if (entity == null) {
            throw new NotFoundException("Software: " + uuid + "Not found");
        }
        Software software = new Software(entity.getSoftwareID(),
                entity.getName(), entity.getVersion(), entity.getVendorEntity().getName(), entity.getVendorEntity().getVendorId());
        return  software;
    }



    public Set<Software> getAllSoftwares()
    {
        Iterable<SoftwareEntity> entityList = softwareRepository.findAll();
        Set <Software> softwares = new HashSet<>();
        for (SoftwareEntity entity : entityList)
        {
            Software software = new Software(entity.getSoftwareID(),
                    entity.getName(), entity.getVersion(), entity.getVendorEntity().getName(), entity.getVendorEntity().getVendorId());

            softwares.add(software);
        }
        return softwares;
    }

    private VendorEntity getVendorEntity(String vendorID) throws NotFoundException
    {
        return vendorService.getVendorEntity(vendorID);

    }

    public SoftwareEntity getSoftwareEntity(String softwareID) throws NotFoundException
    {
        SoftwareEntity entity = softwareRepository.findBySoftwareID(softwareID);
        if (entity == null)
        {
            throw new NotFoundException("Vendor Not found");
        }
        return entity;
    }
}
