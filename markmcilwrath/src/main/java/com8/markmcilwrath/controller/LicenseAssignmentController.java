package com8.markmcilwrath.controller;


import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.service.LicenseAssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/license_assign")
public class LicenseAssignmentController
{
    private LicenseAssignmentService licenseAssignmentService;

    public LicenseAssignmentController(LicenseAssignmentService licenseAssignmentService)
    {
        this.licenseAssignmentService = licenseAssignmentService;
    }

    @PostMapping("/add/admin")
    public ResponseEntity<LicenseAssignment> addLicenseAssignment (@Valid @RequestBody LicenseAssignment licenseAssignment)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                licenseAssignmentService.saveApproved(licenseAssignment.getLicense_key(), licenseAssignment.getUser_id()));

    }

    @PutMapping("update")
    public ResponseEntity<LicenseAssignment> updateLicenseAssignment (@Valid @RequestBody LicenseAssignment licenseAssignment)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                licenseAssignmentService.update(licenseAssignment));
    }

    @GetMapping("/{user_ID}")
    public ResponseEntity<Set<LicenseAssignment>> getAllAssignmentsForUser(@PathVariable String user_ID)
    {
        return ResponseEntity.ok(licenseAssignmentService.getAllAssignmentsByUser(user_ID));
    }

    @GetMapping()
    public ResponseEntity<Set<LicenseAssignment>> getAllAssignments()
    {
        return ResponseEntity.ok(licenseAssignmentService.getAllAssignments());
    }



}
