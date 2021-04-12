package com8.markmcilwrath.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "asset")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "asset_tag", nullable = false, unique = true)
    private String assetTag;

    @Getter
    @Setter
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Getter
    @Setter
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "hardware_id")
    @Getter
    @Setter
    private HardwareEntity hardwareEntity;

    public AssetEntity(String assetTag,
                       String serialNumber,
                       LocalDate purchaseDate,
                       HardwareEntity hardwareEntity)
    {
        this.assetTag = assetTag;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.hardwareEntity = hardwareEntity;
    }
}
