package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.Vendor;
import com8.markmcilwrath.domain.entity.VendorEntity;
import com8.markmcilwrath.repository.VendoreRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VendorService {


    private VendoreRepository vendoreRepository;


    public VendorService(VendoreRepository vendoreRepository) {
        this.vendoreRepository = vendoreRepository;
    }

    public Vendor save(String name) {
        VendorEntity createEntity = new VendorEntity(UUID.randomUUID().toString(), name);
        VendorEntity createdEntity = vendoreRepository.save(createEntity);
        return new Vendor(createdEntity.getVendorId(), createdEntity.getName());
    }


    public void delete(String uuid) {
        vendoreRepository.deleteByVendorId(uuid);
    }

    public Vendor getVendor(String uuid) throws NotFoundException {
        VendorEntity entity = vendoreRepository.findByVendorId(uuid);

        if (entity == null) {
            throw new NotFoundException("Vendore: " + uuid + "Not found");
        }

        Vendor vendor = new Vendor(entity.getVendorId(), entity.getName());
        return vendor;

    }

    public Set<Vendor> getAllVendors() {
        Iterable<VendorEntity> entityList = vendoreRepository.findAll();
        Set<Vendor> vendorSet = new HashSet<>();
        for(VendorEntity entity: entityList) {
            Vendor vendor = new Vendor(entity.getVendorId(), entity.getName());
            vendorSet.add(vendor);
        }
        return vendorSet;
    }


}
