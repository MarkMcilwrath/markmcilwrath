package com8.markmcilwrath.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "software")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoftwareEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "software_id", nullable = false, unique = true)
    private String softwareID;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "version", nullable = false)
    private String version;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "vendor_id")
    @Getter
    @Setter
    private VendorEntity vendorEntity;

    public SoftwareEntity(String softwareID, String name, String version, VendorEntity vendorEntity) {
        this.softwareID = softwareID;
        this.name = name;
        this.version = version;
        this.vendorEntity = vendorEntity;
    }
}
