package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.Asset;
import com8.markmcilwrath.domain.entity.AssetEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AssetRepository extends CrudRepository<AssetEntity, Long>
{
    AssetEntity findByAssetTag (String assetTag);

    @Transactional
    void deleteByAssetTag(String assetTag);
}
