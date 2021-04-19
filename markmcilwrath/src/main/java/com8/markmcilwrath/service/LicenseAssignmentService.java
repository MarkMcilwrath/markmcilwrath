package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

//import java.time.LocalDate;
import java.time.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class LicenseAssignmentService {

    private LicenseAssignmentRepository licenseAssignmentRepository;
    private LicenseService licenseService;
    private UserService userService;

    public LicenseAssignmentService (LicenseAssignmentRepository licenseAssignmentRepository, LicenseService licenseService,
                                     UserService userService)
    {
        this.licenseAssignmentRepository = licenseAssignmentRepository;
        this.licenseService = licenseService;
        this.userService = userService;
    }

    public LicenseAssignment saveApproved (String licenseKey, String userID)
    {
        LicenseEntity licenseEntity = null;
        try {
            licenseEntity = getLicenseEntity(licenseKey);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        UserEntity userEntity = null;
        try {
            userEntity = getUserEntity(userID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        LocalDate assignmentDate = LocalDate.now();
        Boolean approved = true;

        LicenseAssignmentEntity createEntity = new LicenseAssignmentEntity(UUID.randomUUID().toString(),
                 licenseEntity,  userEntity,  assignmentDate, approved);

        LicenseAssignmentEntity createdEntity = licenseAssignmentRepository.save(createEntity);

        return new LicenseAssignment(createdEntity.getUUID(), createdEntity.getLicenseEntity().getLicenseKey(),
                createdEntity.getUserEntity().getUserId(), createdEntity.getAssignmentDate(), createdEntity.getApproved());
    }



    public LicenseAssignment update (LicenseAssignment licenseAssignment)
    {
        LicenseEntity licenseEntity = null;
        try {
            licenseEntity = getLicenseEntity(licenseAssignment.getLicense_key());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        UserEntity userEntity = null;
        try {
            userEntity = getUserEntity(licenseAssignment.getUser_id());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        LicenseAssignmentEntity updateEntity = licenseAssignmentRepository.findByUUID(licenseAssignment.getUUID());
        updateEntity.setLicenseEntity(licenseEntity);
        updateEntity.setUserEntity(userEntity);
        updateEntity.setAssignmentDate(licenseAssignment.getAssignmentDate());
        updateEntity.setApproved((licenseAssignment.getApproved()));
        licenseAssignmentRepository.save(updateEntity);
        return new LicenseAssignment(updateEntity.getUUID(), updateEntity.getLicenseEntity().getLicenseKey(), updateEntity.getUserEntity().getUserId(), updateEntity.getAssignmentDate(), updateEntity.getApproved());
    }



    public Set<LicenseAssignment> getAllAssignments()
    {
        Iterable<LicenseAssignmentEntity> entityList = licenseAssignmentRepository.findAll();
        Set<LicenseAssignment> assignments = new HashSet<>();

        for (LicenseAssignmentEntity entity : entityList) {
            LicenseAssignment assignment = new LicenseAssignment(
                    entity.getUUID(),
                    entity.getLicenseEntity().getLicenseKey(),
                    entity.getUserEntity().getUserId(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getLicenseEntity().getSoftwareEntity().getName(),
                    entity.getLicenseEntity().getSoftwareEntity().getVersion());
            assignments.add(assignment);
        }
        return assignments;
    }

    public Set<LicenseAssignment> getAllAssignmentsByUser(String userId)
    {
        Iterable<LicenseAssignmentEntity> entityList = licenseAssignmentRepository.findByUserID(userId);
        Set<LicenseAssignment> userAssignments = new HashSet<>();


        for (LicenseAssignmentEntity entity : entityList)
        {
            LicenseAssignment assignment = new LicenseAssignment(
                    entity.getUUID(),
                    entity.getLicenseEntity().getLicenseKey(),
                    entity.getUserEntity().getUserId(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getLicenseEntity().getSoftwareEntity().getName(),
                    entity.getLicenseEntity().getSoftwareEntity().getVersion());
            userAssignments.add(assignment);

        }
        return userAssignments;
    }

    private  LicenseEntity getLicenseEntity(String licenseKey) throws NotFoundException
    {
        return licenseService.getLicenseEntity(licenseKey);
    }

    private UserEntity getUserEntity (String userID) throws NotFoundException
    {
        return userService.getUserEntity(userID);
    }
}
