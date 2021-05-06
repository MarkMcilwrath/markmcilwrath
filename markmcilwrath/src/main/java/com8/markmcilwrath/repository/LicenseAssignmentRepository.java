package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LicenseAssignmentRepository extends CrudRepository<LicenseAssignmentEntity, Long>
{
    LicenseAssignmentEntity findByUUID (String UUID);

    LicenseAssignmentEntity findBylicenseEntity (LicenseEntity licenseEntity);

    Iterable<LicenseAssignmentEntity> findIterableByUUID (String UUID);

    Iterable<LicenseAssignmentEntity> findByUserEntity(UserEntity userEntity);


    Iterable<LicenseAssignmentEntity> findByApproved(Boolean approved);

    @Transactional
    void deleteByUUID (String UUID);
}
