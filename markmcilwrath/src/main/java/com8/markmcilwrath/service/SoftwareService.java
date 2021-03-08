package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.repository.SoftwareRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class SoftwareService {
    private SoftwareRepository softwareRepository;

    public SoftwareService (SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public Software save(String name, String version)
    {
        SoftwareEntity createEntity = new SoftwareEntity(UUID.randomUUID().toString(), name, version);
        SoftwareEntity createdEntity = softwareRepository.save(createEntity);
        return new Software(createdEntity.getSoftwareID(),
                createdEntity.getName(), createdEntity.getVersion());
    };

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
                entity.getName(), entity.getVersion());
        return  software;
    }

    public Set<Software> getAllSoftwares()
    {
        Iterable<SoftwareEntity> entityList = softwareRepository.findAll();
        Set <Software> softwares = new HashSet<>();
        for (SoftwareEntity entity : entityList)
        {
            Software software = new Software(entity.getSoftwareID(),
                    entity.getName(), entity.getVersion());
            softwares.add(software);
        }
        return softwares;
    }
}
