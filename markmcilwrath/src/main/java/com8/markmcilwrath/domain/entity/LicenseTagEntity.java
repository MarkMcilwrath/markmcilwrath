package com8.markmcilwrath.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "license_tag")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseTagEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "license_tag_id", nullable = false, unique = true)
    private String licenseTagId;

    @Getter
    @Setter
    @Column(name = "tag_key", nullable = false)
    private String tagKey;

    @Getter
    @Setter
    @Column(name = "tag_value", nullable = false)
    private String tagValue;

    @OneToOne  (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "assignment_id")
    @Getter
    @Setter
    private LicenseAssignmentEntity licenseAssignmentEntity;

    public LicenseTagEntity (String licenseTagId,
                             String tagKey,
                             String tagValue,
                             LicenseAssignmentEntity licenseAssignmentEntity)
    {
        this.licenseTagId=licenseTagId;
        this.tagKey=tagKey;
        this.tagValue=tagValue;
        this.licenseAssignmentEntity=licenseAssignmentEntity;
    }
}