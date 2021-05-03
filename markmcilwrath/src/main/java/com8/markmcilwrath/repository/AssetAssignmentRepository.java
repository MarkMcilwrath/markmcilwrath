package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.AssetAssignmentEntity;
import com8.markmcilwrath.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AssetAssignmentRepository extends CrudRepository<AssetAssignmentEntity, Long>
{
    AssetAssignmentEntity findByUUID (String UUID);

    Iterable<AssetAssignmentEntity> findIterableByUUID (String UUID);
    Iterable<AssetAssignmentEntity> findByUserEntity (UserEntity userEntity);
    Iterable<AssetAssignmentEntity> findByApproved (Boolean approved);

    @Transactional
    void deleteByUUID (String UUID);
}
