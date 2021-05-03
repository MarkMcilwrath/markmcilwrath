package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AssetAssignment
{
    private String UUID;

    @NotBlank(message = "Asset Tag is mandatory")
    private String assetTag;

    @NotBlank(message = "User ID is mandatory")
    private String userID;

    @PastOrPresent
    private LocalDate assignmentDate;

    private Boolean approved;

    private  String email;
    private String name;
    private  String model;

    public AssetAssignment (String UUID,
                            String assetTag,
                            String userID,
                            LocalDate assignmentDate,
                            Boolean approved)
    {
        this.UUID=UUID;
        this.assetTag=assetTag;
        this.userID=userID;
        this.assignmentDate=assignmentDate;
        this.approved=approved;
    }

    public AssetAssignment (String UUID,
                            String assetTag,
                            String userID,
                            LocalDate assignmentDate,
                            Boolean approved,
                            String email,
                            String name,
                            String model)
    {
        this.UUID=UUID;
        this.assetTag=assetTag;
        this.userID=userID;
        this.assignmentDate=assignmentDate;
        this.approved=approved;
        this.email=email;
        this.name=name;
        this.model=model;
    }
}
