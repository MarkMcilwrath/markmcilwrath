package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.AssetAssignment;
import com8.markmcilwrath.domain.entity.AssetAssignmentEntity;
import com8.markmcilwrath.domain.entity.AssetEntity;
import com8.markmcilwrath.domain.entity.UserEntity;
import com8.markmcilwrath.repository.AssetAssignmentRepository;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AssetAssignmentService
{
    private AssetAssignmentRepository assetAssignmentRepository;
    private AssetService assetService;
    private UserService userService;

    public AssetAssignmentService (AssetAssignmentRepository assetAssignmentRepository,
                                   AssetService assetService,
                                   UserService userService)
    {
        this.assetAssignmentRepository = assetAssignmentRepository;
        this.assetService = assetService;
        this.userService = userService;
    }

    public AssetAssignment saveApproved (String assetTag, String userID)
    {
        AssetEntity assetEntity = null;
        try {
           assetEntity = getAssetEntity(assetTag);
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

        AssetAssignmentEntity createEntity = new AssetAssignmentEntity(UUID.randomUUID().toString(),
                assetEntity, userEntity, assignmentDate, approved);

        AssetAssignmentEntity createdEntity = assetAssignmentRepository.save(createEntity);

        return new AssetAssignment(createdEntity.getUUID(), createdEntity.getAssetEntity().getAssetTag(),
                createdEntity.getUserEntity().getUserId(), createdEntity.getAssignmentDate(), createdEntity.getApproved());
    }

    public AssetAssignment saveAwaitingApproval (String assetTag, String userID)
    {
        AssetEntity assetEntity = null;
        try {
            assetEntity = getAssetEntity(assetTag);
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

        AssetAssignmentEntity createEntity = new AssetAssignmentEntity(UUID.randomUUID().toString(),
                assetEntity, userEntity, assignmentDate, approved);

        AssetAssignmentEntity createdEntity = assetAssignmentRepository.save(createEntity);

        return new AssetAssignment(createdEntity.getUUID(), createdEntity.getAssetEntity().getAssetTag(),
                createdEntity.getUserEntity().getUserId(), createdEntity.getAssignmentDate(), createdEntity.getApproved());
    }

    public AssetAssignment approval (String assignmentID)
    {
        AssetAssignmentEntity approvedEntity = assetAssignmentRepository.findByUUID(assignmentID);
        approvedEntity.setApproved(true);
        assetAssignmentRepository.save(approvedEntity);
        return new AssetAssignment(approvedEntity.getUUID(), approvedEntity.getAssetEntity().getAssetTag(), approvedEntity.getUserEntity().getUserId(),
                approvedEntity.getAssignmentDate(), approvedEntity.getApproved());
    }

    public void delete(String assignmentID)
    {
        assetAssignmentRepository.deleteByUUID(assignmentID);
    }

    public AssetAssignment getAssignmentByUUID (String assignmentID) throws NotFoundException
    {
        AssetAssignmentEntity entity = assetAssignmentRepository.findByUUID(assignmentID);

        if (entity == null)
        {
            throw new NotFoundException("License Not Found");
        }

        AssetAssignment assignment = new AssetAssignment(entity.getUUID(),
                entity.getAssetEntity().getAssetTag(),
                entity.getUserEntity().getUserId(),
                entity.getAssignmentDate(),
                entity.getApproved(),
                entity.getUserEntity().getEmail(),
                entity.getAssetEntity().getHardwareEntity().getName(),
                entity.getAssetEntity().getHardwareEntity().getModel());
        return assignment;
    }

    public Set<AssetAssignment> getAssignmentAsIterable (String assignmentID) throws NotFoundException
    {
        Iterable<AssetAssignmentEntity> entityList = assetAssignmentRepository.findIterableByUUID(assignmentID);
        Set<AssetAssignment> assignments = new HashSet<>();

        for (AssetAssignmentEntity entity : entityList) {
            AssetAssignment assignment = new AssetAssignment(
                    entity.getUUID(),
                    entity.getAssetEntity().getAssetTag(),
                    entity.getUserEntity().getUserId(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssetEntity().getHardwareEntity().getName(),
                    entity.getAssetEntity().getHardwareEntity().getModel());
            assignments.add(assignment);
        }
        return assignments;
    }

    public  Set<AssetAssignment> getAllAssignments()
    {
        Iterable<AssetAssignmentEntity> entityList = assetAssignmentRepository.findAll();
        Set<AssetAssignment> assignments = new HashSet<>();

        for (AssetAssignmentEntity entity : entityList) {
            AssetAssignment assignment = new AssetAssignment(
                    entity.getUUID(),
                    entity.getAssetEntity().getAssetTag(),
                    entity.getUserEntity().getUserId(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssetEntity().getHardwareEntity().getName(),
                    entity.getAssetEntity().getHardwareEntity().getModel());
            assignments.add(assignment);
        }
        return assignments;
    }

    public  Set<AssetAssignment> getAllAssignmentsByUser(String userID)
    {
        UserEntity userEntity = null;
        try {
            userEntity = getUserEntity(userID);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        Iterable<AssetAssignmentEntity> entityList = assetAssignmentRepository.findByUserEntity(userEntity);
        Set<AssetAssignment> assignments = new HashSet<>();

        for (AssetAssignmentEntity entity : entityList)
        {
            AssetAssignment assignment = new AssetAssignment(
                    entity.getUUID(),
                    entity.getAssetEntity().getAssetTag(),
                    entity.getUserEntity().getUserId(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssetEntity().getHardwareEntity().getName(),
                    entity.getAssetEntity().getHardwareEntity().getModel());
            assignments.add(assignment);
        }

        return assignments;
    }

    public  Set<AssetAssignment> getAllAssignmentsNotApproved()
    {
        Iterable<AssetAssignmentEntity> entityList = assetAssignmentRepository.findByApproved(false);
        Set<AssetAssignment> assignments = new HashSet<>();

        for (AssetAssignmentEntity entity : entityList) {
            AssetAssignment assignment = new AssetAssignment(
                    entity.getUUID(),
                    entity.getAssetEntity().getAssetTag(),
                    entity.getUserEntity().getUserId(),
                    entity.getAssignmentDate(),
                    entity.getApproved(),
                    entity.getUserEntity().getEmail(),
                    entity.getAssetEntity().getHardwareEntity().getName(),
                    entity.getAssetEntity().getHardwareEntity().getModel());
            assignments.add(assignment);
        }
        return assignments;
    }

    private AssetEntity getAssetEntity (String assetTag) throws NotFoundException
    {
        return assetService.getAssetEntity(assetTag);
    }

    private UserEntity getUserEntity (String userID) throws NotFoundException
    {
        return userService.getUserEntity(userID);
    }
}
