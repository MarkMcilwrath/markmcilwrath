package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.domain.entity.HardwareEntity;
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

    public HardwareService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public Hardware save(String name, String version)
    {
        HardwareEntity createEntity = new HardwareEntity(UUID.randomUUID().toString(), name, version);
        HardwareEntity createdEntity = hardwareRepository.save(createEntity);
        return new Hardware(createdEntity.getHardwareID(), createdEntity.getName(), createdEntity.getModel());
    }

    public Hardware update(Hardware hardware)throws InvocationTargetException, IllegalAccessException
    {
        HardwareEntity updateEntity = hardwareRepository.findByHardwareID((hardware.getUuid()));
        updateEntity.setName(hardware.getName());
        updateEntity.setModel(hardware.getModel());

        hardwareRepository.save(updateEntity);
        return  new Hardware(updateEntity.getHardwareID(), updateEntity.getName(), updateEntity.getModel());
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
        Hardware hardware = new Hardware(entity.getHardwareID(), entity.getName(), entity.getModel());
        return  hardware;
    }

    public Set<Hardware> getAllHardware() {
        Iterable<HardwareEntity> entitylist = hardwareRepository.findAll();
        Set<Hardware> hardwares = new HashSet<>();
        for (HardwareEntity entity : entitylist) {
            Hardware hardware = new Hardware(entity.getHardwareID(),
                    entity.getName(), entity.getModel());
            hardwares.add(hardware);
        }
        return hardwares;
    }
}
