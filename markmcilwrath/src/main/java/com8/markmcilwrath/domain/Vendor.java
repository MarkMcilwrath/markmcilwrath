package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
public class Vendor {


    private String uuid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    public Vendor(String name)
    {
        this.name=name;
    }

    public Vendor(String uuid,
                  String name)
    {
        this.uuid = uuid;
        this.name = name;
    }
}
