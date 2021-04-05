package com8.markmcilwrath.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class Software {

    private String uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Version is mandatory")
    private String version;

   // private List<License> licenses;

    public Software(
            String uuid,
            String name,
            String version)
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
    }

}
