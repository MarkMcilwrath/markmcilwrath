package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.HardwareEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface HardwareRepository extends CrudRepository<HardwareEntity, Long> {

    HardwareEntity findByName(String name);
    HardwareEntity findByHardwareID (String uuid);

    @Transactional
    void deleteByHardwareID(String uuid);
}
