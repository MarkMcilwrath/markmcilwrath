package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseArchive;
import com8.markmcilwrath.service.LicenseArchiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.Set;

@RestController
@RequestMapping(value = "/license_archive")
public class LicenseArchiveController {
    private LicenseArchiveService licenseArchiveService;

    public LicenseArchiveController (LicenseArchiveService licenseArchiveService)
    {
        this.licenseArchiveService=licenseArchiveService;
    }

    @GetMapping()
    public ResponseEntity<Set<LicenseArchive>> getAllLicenses()
    {
        return ResponseEntity.ok(licenseArchiveService.getAllArchivedLicense());
    }
}
