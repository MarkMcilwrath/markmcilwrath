package com8.markmcilwrath.repository;

import com8.markmcilwrath.domain.Asset;
import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.domain.entity.AssetEntity;
import com8.markmcilwrath.domain.entity.HardwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AssetRepository extends CrudRepository<AssetEntity, Long>
{
    AssetEntity findByAssetTag (String assetTag);

    Iterable<AssetEntity> findByHardwareEntity (HardwareEntity hardwareEntity);

    @Transactional
    void deleteByAssetTag(String assetTag);
}
