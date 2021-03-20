package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface VendoreRepository extends CrudRepository<VendorEntity, Long> {

    VendorEntity findByName(String name);
    VendorEntity findByVendorId(String uuid);


    @Transactional
    void deleteByVendorId(String uuid);

    @Transactional
    void deleteByName(String name);
}
