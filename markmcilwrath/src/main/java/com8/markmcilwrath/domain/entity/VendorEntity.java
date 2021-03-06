package com8.markmcilwrath.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vendor")
@EqualsAndHashCode()
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "internal_id")
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "vendor_id", nullable = false, unique = true)
    private String vendorId;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "vendor_name", nullable = false, unique = true)
    private String name;


    public VendorEntity(String vendorId, String name) {
        this.vendorId = vendorId;
        this.name = name;
    }

}
