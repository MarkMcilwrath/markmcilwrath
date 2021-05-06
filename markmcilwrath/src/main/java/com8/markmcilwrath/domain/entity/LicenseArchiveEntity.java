package com8.markmcilwrath.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "license_archive")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseArchiveEntity implements Serializable
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

    @Getter
    @Setter
    @Column(name = "software_id")
    private Long software_ID;

    @Getter
    @Setter
    @Column(name = "software_name")
    private String software_name;

    @Getter
    @Setter
    @Column(name = "software_version")
    private String software_version;


    public LicenseArchiveEntity(String licenseKey,
                                LocalDate purchaseDate,
                                LocalDate expiryDate,
                                Long software_ID,
                                String software_name,
                                String software_version)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.software_ID = software_ID;
        this.software_name=software_name;
        this.software_version=software_version;
    }
}