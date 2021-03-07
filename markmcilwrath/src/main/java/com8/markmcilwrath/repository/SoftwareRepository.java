package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SoftwareRepository extends CrudRepository<SoftwareEntity, Long>
{
    SoftwareEntity findByName(String name);
    SoftwareEntity findBySoftwareID (String uuid);

    @Transactional
    void deleteBySoftwareID(String uuid);
}
