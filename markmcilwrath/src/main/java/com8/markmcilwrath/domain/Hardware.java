package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
public class Hardware {

    private String uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Version is mandatory")
    private String version;

   // private List<Assets> assets;

    public Hardware(
            String uuid,
            String name,
            String version
          //  List assets
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.version = version;
     //   this.assets = assets;
    }
}
