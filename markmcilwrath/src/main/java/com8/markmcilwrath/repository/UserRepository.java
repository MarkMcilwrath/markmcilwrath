package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(String uuid);


    @Transactional
    void deleteByUserId(String uuid);
}
