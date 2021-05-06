package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.entity.AssetAssignmentEntity;
import com8.markmcilwrath.domain.entity.AssetTagEntity;

import org.springframework.data.repository.CrudRepository;

public interface AssetTagRepository extends CrudRepository<AssetTagEntity, Long>
{
    Iterable<AssetTagEntity> findIterableByAssetAssignmentEntity (AssetAssignmentEntity assetAssignmentEntity);
}
