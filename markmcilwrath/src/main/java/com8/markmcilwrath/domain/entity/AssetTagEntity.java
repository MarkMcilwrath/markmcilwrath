package com8.markmcilwrath.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "asset_tag")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetTagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "asset_tag_id", nullable = false, unique = true)
    private String assetTagId;

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
    private AssetAssignmentEntity assetAssignmentEntity;

    public AssetTagEntity (String assetTagId, String tagKey, String tagValue, AssetAssignmentEntity assetAssignmentEntity)
    {
        this.assetTagId=assetTagId;
        this.tagKey=tagKey;
        this.tagValue=tagValue;
        this.assetAssignmentEntity=assetAssignmentEntity;
    }
}
