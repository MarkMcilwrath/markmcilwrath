package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.domain.LicenseTag;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.LicenseTagEntity;
import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import com8.markmcilwrath.repository.LicenseTagRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

//import java.time.LocalDate;
import java.lang.reflect.GenericArrayType;
import java.time.*;
import java.util.*;

@Service
public class LicenseAssignmentService {

    private LicenseAssignmentRepository licenseAssignmentRepository;
    private LicenseService licenseService;
    private UserService userService;
    private LicenseTagRepository licenseTagRepository;
    private LicenseTagService licenseTagService;

    public LicenseAssignmentService (LicenseAssignmentRepository licenseAssignmentRepository,
                                     LicenseService licenseService,
                                     UserService userService,
                                     LicenseTagService licenseTagService,
                                     LicenseTagRepository licenseTagRepository)
    {
        this.licenseAssignmentRepository = licenseAssignmentRepository;
        this.licenseService = licenseService;
        this.userService = userService;
        this.licenseTagService=licenseTagService;
        this.licenseTagRepository=licenseTagRepository;
    }

    public LicenseAssignment saveApproved (String licenseKey, String userID,  Map<String, String> tags)
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

        licenseTagService.saveTags(createdEntity, tags);

        return new LicenseAssignment(createdEntity.getUUID(), createdEntity.getLicenseEntity().getLicenseKey(),
                createdEntity.getUserEntity().getUserId(), createdEntity.getAssignmentDate(), createdEntity.getApproved(), tags);
    }

    public LicenseAssignment saveAwaitingApproval (String licenseKey, String userID
//            , Map<String, String> tags
    )
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
        Boolean approved = false;

        LicenseAssignmentEntity createEntity = new LicenseAssignmentEntity(UUID.randomUUID().toString(),
                licenseEntity,  userEntity,  assignmentDate, approved);

        LicenseAssignmentEntity createdEntity = licenseAssignmentRepository.save(createEntity);

//        licenseTagService.saveTags(createdEntity, tags);

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

    public LicenseAssignment approval (String assignmentID)
    {
        LicenseAssignmentEntity updateEntity = licenseAssignmentRepository.findByUUID(assignmentID);
        updateEntity.setApproved(true);
        licenseAssignmentRepository.save(updateEntity);
        return new LicenseAssignment(updateEntity.getUUID(), updateEntity.getLicenseEntity().getLicenseKey(), updateEntity.getUserEntity().getUserId(), updateEntity.getAssignmentDate(), updateEntity.getApproved());
    }

    public void delete(String assignmentID)
    {
        licenseAssignmentRepository.deleteByUUID(assignmentID);
    }

    public Set<LicenseAssignment> getAssignmentAsIterable (String assignmentID) throws NotFoundException
    {
        Iterable<LicenseAssignmentEntity> entityList = licenseAssignmentRepository.findIterableByUUID(assignmentID);
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
                    entity.getLicenseEntity().getSoftwareEntity().getVersion(),
                    getTags(entity.getUUID())
                    );
            assignments.add(assignment);
        }
        return assignments;
    }

    public Set<LicenseAssignment> getAllAssignmentsByUser(String userId)
    {

        UserEntity userEntity = null;
        try {
            userEntity = getUserEntity(userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        Iterable<LicenseAssignmentEntity> entityList = licenseAssignmentRepository.findByUserEntity(userEntity);
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

    public Set<LicenseAssignment> getAllAssignmentsNotApproved ()
    {
        Iterable<LicenseAssignmentEntity> entityList = licenseAssignmentRepository.findByApproved(false);
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

    public LicenseAssignment getAssignmentByUUID (String assignmentID) throws NotFoundException
    {
        LicenseAssignmentEntity entity = licenseAssignmentRepository.findByUUID(assignmentID);

        if (entity == null)
        {
            throw new NotFoundException("License Not Found");
        }

        LicenseAssignment assignment = new LicenseAssignment(entity.getUUID(),
                entity.getLicenseEntity().getLicenseKey(),
                entity.getUserEntity().getUserId(),
                entity.getUserEntity().getEmail(),
                entity.getAssignmentDate(),
                entity.getApproved(),
                entity.getLicenseEntity().getSoftwareEntity().getName(),
                entity.getLicenseEntity().getSoftwareEntity().getVersion());
        return assignment;
    }

    private  LicenseEntity getLicenseEntity(String licenseKey) throws NotFoundException
        {
            return licenseService.getLicenseEntity(licenseKey);
        }

    private UserEntity getUserEntity (String userID) throws NotFoundException
        {
            return userService.getUserEntity(userID);
        }

    public LicenseAssignmentEntity getLicenseAssignmentEntity (String assignmentID)
    {
        LicenseAssignmentEntity entity = licenseAssignmentRepository.findByUUID(assignmentID);
        return entity;
    }

    public Set<LicenseTag> getTags(String assignmentId) {

        Iterable<LicenseTagEntity> tags = licenseTagRepository.findIterableByLicenseAssignmentEntity(getLicenseAssignmentEntity(assignmentId));
        Set<LicenseTag> tagSet = new HashSet<>();

        for (LicenseTagEntity entity : tags)
        {
            LicenseTag licenseTag = new LicenseTag(entity.getLicenseTagId(),
                    entity.getTagKey(), entity.getTagValue(), assignmentId);
            tagSet.add(licenseTag);
        }
        return tagSet;
    }

//    public Set<LicenseAssignment> getAssignmentsWithLocationTag() throws NotFoundException
//    {
//        Set<LicenseAssignment> locationAssignments = new HashSet<>();
//
//        Iterable<LicenseAssignmentEntity> listOfAssignment = licenseAssignmentRepository.findAll();
//        for (LicenseAssignmentEntity entity : listOfAssignment)
//        {
//            LicenseTagEntity licenseTagEntity= licenseTagRepository.findByTagKeyAndLicenseAssignmentEntity
//                  ("location" ,getLicenseAssignmentEntity(entity.getUUID()));
//
//            LicenseAssignment newLicense = new LicenseAssignment(
//                  licenseTagEntity.getLicenseAssignmentEntity().getUUID(),
//                  licenseTagEntity.getLicenseAssignmentEntity().getLicenseEntity().getLicenseKey(),
//                  licenseTagEntity.getLicenseAssignmentEntity().getUserEntity().getUserId(),
//                  licenseTagEntity.getTagValue());
//
//          locationAssignments.add(newLicense);
//        }
//        return locationAssignments;
//    }

}
