package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AssetTag {
    private String assetTagId;

    private String tagKey;

    private String tagValue;

    private String assignment_id;

    public AssetTag (String assetTagId,
                     String tagKey,
                     String tagValue,
                     String assignment_id)
    {
        this.assetTagId=assetTagId;
        this.tagKey=tagKey;
        this.tagValue=tagValue;
        this.assignment_id=assignment_id;
    }
}
