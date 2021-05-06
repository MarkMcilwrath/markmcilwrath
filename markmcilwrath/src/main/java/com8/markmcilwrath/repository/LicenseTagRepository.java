package com8.markmcilwrath.repository;


//import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.LicenseTagEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface LicenseTagRepository extends CrudRepository<LicenseTagEntity, Long>
{
    LicenseTagEntity findByLicenseTagId (String licenseTagId);

    LicenseTagEntity findByTagKey (String tag_Key);


    LicenseTagEntity findByTagKeyAndLicenseAssignmentEntity(String tagKey, LicenseAssignmentEntity licenseAssignmentEntity);

    Iterable<LicenseTagEntity> findIterableByLicenseAssignmentEntity (LicenseAssignmentEntity licenseAssignmentEntity);

    @Transactional
    void deleteByLicenseTagId (String licenseTagId);
}