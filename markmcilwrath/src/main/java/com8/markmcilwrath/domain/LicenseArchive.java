package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LicenseArchive
{
    @NotBlank(message = "Key is mandatory")
    private String licenseKey;

    @NotNull(message = "PurchaseDate is mandatory")
    @PastOrPresent
    private LocalDate purchaseDate;

    @Future
    private LocalDate expiryDate;

    private String softwareName;
    private Long softwareID;
    private String softwareVersion;


    public LicenseArchive(
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
    }

    public LicenseArchive(
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate,
            Long softwareID)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.softwareID = softwareID;
    }

//    public License (
//            String licenseKey,
//            LocalDate purchaseDate,
//            LocalDate expiryDate,
//            String softwareID,
//            String user_ID)
//    {
//        this.licenseKey = licenseKey;
//        this.purchaseDate = purchaseDate;
//        this.expiryDate = expiryDate;
//        this.softwareID = softwareID;
//        this.user_ID = user_ID;
//    }

    public LicenseArchive(
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate,
            String softwareName,
            Long softwareID,
            String softwareVersion)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.softwareName = softwareName;
        this.softwareID = softwareID;
        this.softwareVersion = softwareVersion;
    }

}
