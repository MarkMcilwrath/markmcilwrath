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
@Table(name = "hardware")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HardwareEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "hardware_ID", nullable = false, unique = true)
    private String hardwareID;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "vendor_id")
    @Getter
    @Setter
    private VendorEntity vendorEntity;

    public HardwareEntity(String hardwareID, String name, String model, VendorEntity vendorEntity) {
        this.hardwareID = hardwareID;
        this.name = name;
        this.model = model;
        this.vendorEntity = vendorEntity;
    }

}
