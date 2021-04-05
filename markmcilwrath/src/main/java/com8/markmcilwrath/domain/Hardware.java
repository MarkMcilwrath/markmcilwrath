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

    @NotBlank(message = "Model is mandatory")
    private String model;

   // private List<Assets> assets;

    public Hardware(
            String uuid,
            String name,
            String model
          //  List assets
    )
    {
        this.uuid = uuid;
        this.name = name;
        this.model = model;
     //   this.assets = assets;
    }
}
