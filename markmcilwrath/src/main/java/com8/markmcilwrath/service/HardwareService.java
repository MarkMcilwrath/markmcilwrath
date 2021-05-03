package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.domain.entity.HardwareEntity;
import com8.markmcilwrath.domain.entity.VendorEntity;
import com8.markmcilwrath.repository.HardwareRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class HardwareService {

    private HardwareRepository hardwareRepository;
    private VendorService vendorService;
//    private AssetService assetService;

    public HardwareService(HardwareRepository hardwareRepository,
                           VendorService vendorService
//                           AssetService assetService
    )
    {
        this.hardwareRepository = hardwareRepository;
        this.vendorService = vendorService;
//        this.assetService = assetService;
    }

    public Hardware save(String name, String model,String vendorID)
    {
        VendorEntity vendorEntity = null;
        try {
            vendorEntity = getVendorEntity(vendorID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        HardwareEntity createEntity = new HardwareEntity(UUID.randomUUID().toString(), name, model, vendorEntity);
        HardwareEntity createdEntity = hardwareRepository.save(createEntity);
        return new Hardware(createdEntity.getHardwareID(), createdEntity.getName(), createdEntity.getModel(), vendorEntity.getVendorId());
    }

    public Hardware update(Hardware hardware, String vendorID) throws InvocationTargetException, IllegalAccessException
    {
        VendorEntity vendorEntity = null;
        try {
            vendorEntity = getVendorEntity(vendorID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        HardwareEntity updateEntity = hardwareRepository.findByHardwareID((hardware.getUuid()));
        updateEntity.setName(hardware.getName());
        updateEntity.setModel(hardware.getModel());
        updateEntity.setVendorEntity(vendorEntity);

        hardwareRepository.save(updateEntity);
        return  new Hardware(updateEntity.getHardwareID(), updateEntity.getName(), updateEntity.getModel(), updateEntity.getVendorEntity().getVendorId());
    }

    public void delete(String uuid){
        hardwareRepository.deleteByHardwareID(uuid);
    }

    public Hardware getHardware(String uuid) throws NotFoundException
    {
        HardwareEntity entity = hardwareRepository.findByHardwareID(uuid);

        if (entity == null) {
            throw new NotFoundException("Hardware: " + uuid + " Not Found");
        }
        Hardware hardware = new Hardware(entity.getHardwareID(), entity.getName(), entity.getModel(), entity.getVendorEntity().getVendorId());
        return  hardware;
    }

    public Set<Hardware> getAllHardware() {
        Iterable<HardwareEntity> entitylist = hardwareRepository.findAll();
        Set<Hardware> hardwares = new HashSet<>();
        for (HardwareEntity entity : entitylist) {
            Hardware hardware = new Hardware(entity.getHardwareID(),
                    entity.getName(), entity.getModel(),entity.getVendorEntity().getName(), entity.getVendorEntity().getVendorId());
            hardwares.add(hardware);
        }
        return hardwares;
    }

    public Set<Hardware> getAllHardwareByVendor(String vendorID)
    {
        VendorEntity vendorEntity = null;
        try {
            vendorEntity = getVendorEntity(vendorID);
        }catch (NotFoundException e) {
            e.printStackTrace();
        }

        Iterable<HardwareEntity> entityList = hardwareRepository.findByVendorEntity(vendorEntity);
        Set <Hardware> hardwareSet = new HashSet<>();
        for (HardwareEntity entity : entityList)
        {
            Hardware hardware = new Hardware(
                    entity.getHardwareID(),
                    entity.getName(),
                    entity.getModel()
//                    numberOfAssets(entity.getHardwareID())
            );
            hardwareSet.add(hardware);
        }
        return hardwareSet;
    }

//    public int numberOfAssets (String uuid)
//    {
//        return assetService.numberOfAssets(uuid);
//    }

    private VendorEntity getVendorEntity(String vendorID) throws NotFoundException
    {
        return vendorService.getVendorEntity(vendorID);

    }

    public HardwareEntity getHardwareEntity(String hardwareID) throws NotFoundException
    {
        HardwareEntity entity = hardwareRepository.findByHardwareID(hardwareID);
        if (entity == null)
        {
            throw new NotFoundException("Hardware Not found");
        }
        return entity;
    }
}
