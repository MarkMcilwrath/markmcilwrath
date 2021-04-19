package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LicenseAssignmentRepository extends CrudRepository<LicenseAssignmentEntity, Long>
{
    LicenseAssignmentEntity findByUUID (String UUID);


    Iterable<LicenseAssignmentEntity> findByUserID(String userId);



    @Transactional
    void deleteByUUID (String UUID);
}
