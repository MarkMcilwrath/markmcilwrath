package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.service.LicenseService;
import com8.markmcilwrath.domain.entity.LicenseEntity;
import com8.markmcilwrath.repository.LicenseRepository;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping(value = "/license")
public class LicenseController
{
    private LicenseService licenseService;

    public LicenseController (LicenseService licenseService)
    {
        this.licenseService = licenseService;
    }

    @PostMapping("/add/{softwareID}")
    public ResponseEntity<License> addLicense (@PathVariable String softwareID, @Valid @RequestBody License license)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                licenseService.save(license.getLicenseKey(),
                        license.getPurchaseDate(),license.getExpiryDate(), softwareID)
        );
    }

    @PutMapping("/update/{softwareID}")
    public ResponseEntity<License> updateLicense(@PathVariable String softwareID, @Valid @RequestBody License license) throws InvocationTargetException, IllegalAccessException
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                licenseService.update((license), softwareID));
    }

    @DeleteMapping("/{licenseKey}")
    public ResponseEntity<Void> deleteLicenseById(@PathVariable String licenseKey)
    {
        licenseService.delete(licenseKey);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{licenseKey}")
    public ResponseEntity<License> getLicenseByKey(@PathVariable String licenseKey) throws NotFoundException
    {
        return ResponseEntity.ok().body(licenseService.getLicense(licenseKey));
    }

    @GetMapping("/software/{softwareID}")
    public ResponseEntity<Set<License>> getAllLicenseBySoftware (@PathVariable String softwareID) throws NotFoundException
    {
        return ResponseEntity.ok().body(licenseService.getAllLicenseBySoftware(softwareID));
    }

    @GetMapping()
    public ResponseEntity<Set<License>> getAllLicenses()
    {
        return ResponseEntity.ok(licenseService.getAllLicense());
    }

    @GetMapping("/free")
    public ResponseEntity<Set<License>> getAllFreeLicenses()
    {
        return ResponseEntity.ok(licenseService.getAllFreeLicense());
    }
}
