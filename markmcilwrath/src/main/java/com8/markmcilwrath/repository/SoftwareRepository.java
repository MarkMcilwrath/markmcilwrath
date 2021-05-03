package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SoftwareRepository extends CrudRepository<SoftwareEntity, Long>
{
    SoftwareEntity findByName(String name);
    SoftwareEntity findBySoftwareID (String uuid);

    Iterable<SoftwareEntity> findByVendorEntity (VendorEntity vendorEntity);

    @Transactional
    void deleteBySoftwareID(String uuid);
}
