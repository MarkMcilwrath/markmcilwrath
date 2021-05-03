package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.HardwareEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface HardwareRepository extends CrudRepository<HardwareEntity, Long> {

    HardwareEntity findByName(String name);
    HardwareEntity findByHardwareID (String uuid);

    Iterable<HardwareEntity> findByVendorEntity (VendorEntity vendorEntity);

    @Transactional
    void deleteByHardwareID(String uuid);
}
