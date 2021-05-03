package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.Asset;
import com8.markmcilwrath.domain.entity.AssetAssignmentEntity;
import com8.markmcilwrath.domain.entity.AssetEntity;
import com8.markmcilwrath.domain.entity.HardwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import com8.markmcilwrath.repository.AssetAssignmentRepository;
import com8.markmcilwrath.repository.AssetRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AssetService
{
    private AssetRepository assetRepository;
    private AssetAssignmentRepository assetAssignmentRepository;
    private HardwareService hardwareService;

    public AssetService (AssetRepository assetRepository, HardwareService hardwareService, AssetAssignmentRepository assetAssignmentRepository)
    {
        this.assetRepository = assetRepository;
        this.hardwareService = hardwareService;
        this.assetAssignmentRepository = assetAssignmentRepository;
    }

    public Asset save (String serialNumber, LocalDate purchaseDate, String hardwareID)
    {
        HardwareEntity entity = null;
        try {
            entity = getHardwareEntity(hardwareID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        AssetEntity createEntity = new AssetEntity(UUID.randomUUID().toString(), serialNumber, purchaseDate, entity);
        AssetEntity createdEntity = assetRepository.save(createEntity);
        return new Asset(createdEntity.getAssetTag(), createdEntity.getSerialNumber(),
                createdEntity.getPurchaseDate(), createdEntity.getHardwareEntity().getHardwareID());
    }

    public Asset update(Asset asset, String hardwareID) throws InvocationTargetException, IllegalAccessException
    {
        HardwareEntity hardwareEntity = null;
        try {
            hardwareEntity = getHardwareEntity(hardwareID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        AssetEntity updateEntity = assetRepository.findByAssetTag(asset.getAssetTag());
        updateEntity.setSerialNumber(asset.getSerialNumber());
        updateEntity.setPurchaseDate(asset.getPurchaseDate());
        updateEntity.setHardwareEntity(hardwareEntity);

        assetRepository.save(updateEntity);
        return  new Asset(updateEntity.getAssetTag(), updateEntity.getSerialNumber(), updateEntity.getPurchaseDate(), updateEntity.getHardwareEntity().getHardwareID());
    }

    public void delete(String assetTag)
    {
        assetRepository.deleteByAssetTag(assetTag);
    }

    public Asset getAssetbyAssetTag(String assetTag) throws NotFoundException
    {
        AssetEntity entity = assetRepository.findByAssetTag(assetTag);
        if (entity == null)
        {
            throw new NotFoundException("Asset Not Found");
        }

        Asset asset = new Asset(entity.getAssetTag(), entity.getSerialNumber(), entity.getPurchaseDate(), entity.getHardwareEntity().getHardwareID()
        );
        return  asset;
    }

    public Set<Asset> getAllAsset()
    {
        Iterable<AssetEntity> entityList = assetRepository.findAll();
        Set<Asset> assets = new HashSet<>();
        for (AssetEntity entity : entityList)
        {
            Asset asset = new Asset(entity.getAssetTag(), entity.getSerialNumber(), entity.getPurchaseDate(),
                    entity.getHardwareEntity().getHardwareID(),
                    entity.getHardwareEntity().getName(), entity.getHardwareEntity().getModel());
                    assets.add(asset);
        }
        return assets;
    }

    public Set<Asset> getAllFreeAsset()
    {
        Iterable<AssetEntity> entityList = assetRepository.findAll();
        Set<Asset> assets = new HashSet<>();

        Iterable<AssetAssignmentEntity> assignmentList = assetAssignmentRepository.findAll();
        for (AssetEntity entity : entityList)
        {
            boolean assigned = false;
            for (AssetAssignmentEntity assignmentEntity : assignmentList) {
                String tag = entity.getAssetTag();
                String assignTag = assignmentEntity.getAssetEntity().getAssetTag();
                if (tag == assignTag)
                {
                    assigned = true;
                }
            }
            if (assigned == false)
            {
                Asset asset = new Asset(entity.getAssetTag(), entity.getSerialNumber(), entity.getPurchaseDate(),
                            entity.getHardwareEntity().getHardwareID(),
                            entity.getHardwareEntity().getName(), entity.getHardwareEntity().getModel());
                    assets.add(asset);
            }
        }
        return assets;
    }

    public Set<Asset> getAllAssetForHardwareID(String hardwareID)
    {
        HardwareEntity hardwareEntity = null;
        try {
            hardwareEntity = getHardwareEntity(hardwareID);
        }catch (NotFoundException e) {
            e.printStackTrace();
        }

        Iterable<AssetEntity> entityList = assetRepository.findByHardwareEntity(hardwareEntity);
        Set<Asset> assets = new HashSet<>();
        for (AssetEntity entity : entityList)
        {
            Asset asset = new Asset(entity.getAssetTag(), entity.getSerialNumber(), entity.getPurchaseDate(),
                    entity.getHardwareEntity().getHardwareID(),
                    entity.getHardwareEntity().getName(), entity.getHardwareEntity().getModel());
            assets.add(asset);
        }
        return assets;
    }

    private HardwareEntity getHardwareEntity(String hardwareID) throws NotFoundException
    {
        return hardwareService.getHardwareEntity(hardwareID);
    }

    public AssetEntity getAssetEntity (String assetTag) throws NotFoundException
    {
        AssetEntity entity = assetRepository.findByAssetTag(assetTag);
        if (entity == null)
        {
            throw new NotFoundException("Vendor Not found");
        }
        return entity;
    }
}
