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
@Table(name = "license")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "license_key", nullable = false, unique = true)
    private String licenseKey;

    @Getter
    @Setter
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Getter
    @Setter
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "software_id")
    @Getter
    @Setter
    private SoftwareEntity softwareEntity;

    public LicenseEntity(String licenseKey, LocalDate purchaseDate, LocalDate expiryDate, SoftwareEntity softwareEntity)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.softwareEntity = softwareEntity;
    }
}