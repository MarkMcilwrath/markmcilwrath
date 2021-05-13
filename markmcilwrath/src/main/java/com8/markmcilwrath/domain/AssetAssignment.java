package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private Set tagSet;

    private Map<String, String> tags = new HashMap<>();

    public AssetAssignment (
                            String assetTag,
                            String userID,
                            LocalDate assignmentDate)
    {

        this.assetTag=assetTag;
        this.userID=userID;
        this.assignmentDate=assignmentDate;

    }

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
                            Map tags)
    {
        this.UUID=UUID;
        this.assetTag=assetTag;
        this.userID=userID;
        this.assignmentDate=assignmentDate;
        this.approved=approved;
        this.tags=tags;
    }



    public AssetAssignment (String UUID,
                            String assetTag,
                            String userID,
                            LocalDate assignmentDate,
                            Boolean approved,
                            String email,
                            String name,
                            String model,
                            Set tagSet)
    {
        this.UUID=UUID;
        this.assetTag=assetTag;
        this.userID=userID;
        this.assignmentDate=assignmentDate;
        this.approved=approved;
        this.email=email;
        this.name=name;
        this.model=model;
        this.tagSet=tagSet;
    }


}
