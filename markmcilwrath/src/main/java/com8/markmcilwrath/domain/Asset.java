package com8.markmcilwrath.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Asset {

    private String assetTag;

    private String serialNumber;

    private LocalDate purchaseDate;

    private String hardwareID;

    public Asset (String assetTag,
                  String serialNumber,
                  LocalDate purchaseDate)
    {
        this.assetTag = assetTag;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
    }

    public Asset (String assetTag,
                  String serialNumber,
                  LocalDate purchaseDate,
                  String hardwareID)
    {
        this.assetTag = assetTag;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.hardwareID = hardwareID;
    }
}
