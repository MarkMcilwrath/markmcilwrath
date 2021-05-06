package com8.markmcilwrath.service;

import com8.markmcilwrath.domain.AssetTag;
import com8.markmcilwrath.domain.LicenseTag;
import com8.markmcilwrath.domain.entity.AssetAssignmentEntity;
import com8.markmcilwrath.domain.entity.AssetTagEntity;
import com8.markmcilwrath.repository.AssetTagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class AssetTagService {
    private AssetTagRepository assetTagRepository;

    public AssetTagService (AssetTagRepository assetTagRepository)
    {
        this.assetTagRepository=assetTagRepository;
    }

    public Set<AssetTag> saveTags(AssetAssignmentEntity assignment, Map<String, String> tags)
    {
        Set<AssetTag> addedTags = new HashSet<>();

        for(String key : tags.keySet())
        {
            AssetTagEntity createEntity = new AssetTagEntity(UUID.randomUUID().toString(), key, tags.get(key), assignment);
            AssetTagEntity createdEntity = assetTagRepository.save(createEntity);
            addedTags.add(new AssetTag(createdEntity.getAssetTagId(), createdEntity.getTagKey(),
                    createdEntity.getTagValue(), createdEntity.getAssetAssignmentEntity().getUUID()));
        }
        return addedTags;
    }
}
