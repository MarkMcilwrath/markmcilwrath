package com8.markmcilwrath.domain;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class License
{
    @NotBlank(message = "Key is mandatory")
    private String licenseKey;

    @NotNull(message = "PurchaseDate is mandatory")
    @PastOrPresent
    private LocalDate purchaseDate;

    @Future
    private LocalDate expiryDate;

    private String softwareName;
    private String softwareID;
    private String softwareVersion;
    private Boolean assigned;


    public License (
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate)
    {
        this.licenseKey = licenseKey;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
    }

    public License (
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate,
            String softwareID)
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

    public License (
            String licenseKey,
            LocalDate purchaseDate,
            LocalDate expiryDate,
            String softwareName,
            String softwareID,
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
