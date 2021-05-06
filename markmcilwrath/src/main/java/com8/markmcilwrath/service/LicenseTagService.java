package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.domain.LicenseTag;
import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.domain.entity.LicenseTagEntity;
import com8.markmcilwrath.domain.entity.SoftwareEntity;
import com8.markmcilwrath.repository.LicenseAssignmentRepository;
import com8.markmcilwrath.repository.LicenseRepository;
import com8.markmcilwrath.repository.LicenseTagRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class LicenseTagService {
    private LicenseTagRepository licenseTagRepository;
    private LicenseAssignmentService licenseAssignmentService;


    public LicenseTagService (LicenseTagRepository licenseTagRepository)
    {
        this.licenseTagRepository=licenseTagRepository;
    }


    public Set<LicenseTag> saveTags(LicenseAssignmentEntity assignment, Map<String, String> tags)
    {
        Set<LicenseTag> addedTags = new HashSet<>();

        for(String key : tags.keySet())
        {
            LicenseTagEntity createEntity = new LicenseTagEntity(UUID.randomUUID().toString(), key, tags.get(key), assignment);
            LicenseTagEntity createdEntity = licenseTagRepository.save(createEntity);
            addedTags.add(new LicenseTag(createdEntity.getLicenseTagId(), createdEntity.getTagKey(),
                    createdEntity.getTagValue(), createdEntity.getLicenseAssignmentEntity().getUUID()));
        }
        return addedTags;

    }

}


