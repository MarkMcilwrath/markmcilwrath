package com8.markmcilwrath.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Software {

    private String uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Version is mandatory")
    private String version;

    private String vendorName;

    private String vendorID;

    public Software(
            String uuid,
            String name,
            String version)
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
    }

    public Software(
            String uuid,
            String name,
            String version,
            String vendorName,
            String vendorID)
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
        this.vendorName = vendorName;
        this.vendorID = vendorID;
    }

    public Software(
            String uuid,
            String name,
            String version,
            String vendorID)
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
        this.vendorID = vendorID;
    }


}
