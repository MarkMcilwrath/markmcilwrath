package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hardware {

    private String uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Model is mandatory")
    private String model;

    private String vendorName;

    private String vendorID;

    public Hardware(
            String uuid,
            String name,
            String model
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
    }

    public Hardware(
            String uuid,
            String name,
            String model,
            String vendorName,
            String vendorID
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
        this.vendorName = vendorName;
        this.vendorID = vendorID;
    }

    public Hardware(
            String uuid,
            String name,
            String model,
            String vendorID
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
        this.vendorID = vendorID;
    }
}
