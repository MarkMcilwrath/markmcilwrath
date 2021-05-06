package com8.markmcilwrath.domain;

import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LicenseTag {
    private String licenseTagId;

    private String tagKey;

    private String tagValue;

    private String assignment_id;


    public LicenseTag(String licenseTagId,
                             String tagKey,
                             String tagValue,
                             String assignment_id)
    {
        this.licenseTagId=licenseTagId;
        this.tagKey=tagKey;
        this.tagValue=tagValue;
        this.assignment_id=assignment_id;
    }

}
