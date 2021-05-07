package com8.markmcilwrath.domain;

import com8.markmcilwrath.domain.entity.LicenseAssignmentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LicenseAssignment {
    private String UUID;

    @NotBlank(message = "License Key is mandatory")
    private String license_key;

    @NotBlank(message = "User ID is mandatory")
    private String user_id;

    @PastOrPresent
    private LocalDate assignmentDate;

//    @NotNull
    private Boolean approved;

    private String name;
    private  String version;
    private  String email;

    private Set tagSet;

    private Map<String, String> tags = new HashMap<>();

    public LicenseAssignment(String UUID, String license_key, String user_id, LocalDate assignmentDate, Boolean approved)
    {
        this.UUID = UUID;
        this.license_key = license_key;
        this.user_id = user_id;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
    }

    public LicenseAssignment(String UUID,
                             String license_key,
                             String user_id,
                             LocalDate assignmentDate,
                             Boolean approved,
                             Map tags)
    {
        this.UUID = UUID;
        this.license_key = license_key;
        this.user_id = user_id;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
        this.tags=tags;
    }


    public LicenseAssignment(String UUID,
                             String license_key,
                             String user_id,
                             String email,
                             LocalDate assignmentDate,
                             Boolean approved,
                             String name,
                             String version,
                             Map tags)
    {
        this.UUID = UUID;
        this.license_key = license_key;
        this.user_id = user_id;
        this.email = email;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
        this.name = name;
        this.version = version;
        this.tags=tags;
    }

    public LicenseAssignment(String UUID,
                             String license_key,
                             String user_id,
                             String email,
                             LocalDate assignmentDate,
                             Boolean approved,
                             String name,
                             String version,
                             Set tagSet)
    {
        this.UUID = UUID;
        this.license_key = license_key;
        this.user_id = user_id;
        this.email = email;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
        this.name = name;
        this.version = version;
        this.tagSet=tagSet;
    }


    public LicenseAssignment(String UUID,
                             String license_key,
                             String user_id,
                             String email,
                             LocalDate assignmentDate,
                             Boolean approved,
                             String name,
                             String version)
    {
        this.UUID = UUID;
        this.license_key = license_key;
        this.user_id = user_id;
        this.email = email;
        this.assignmentDate = assignmentDate;
        this.approved = approved;
        this.name = name;
        this.version = version;


    }
}
