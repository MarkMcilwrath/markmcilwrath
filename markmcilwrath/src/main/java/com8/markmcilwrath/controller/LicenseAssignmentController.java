package com8.markmcilwrath.controller;


import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.service.LicenseAssignmentService;
import javassist.NotFoundException;
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
    public ResponseEntity<LicenseAssignment> addLicenseAssignmentAdmin (@Valid @RequestBody LicenseAssignment licenseAssignment)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                licenseAssignmentService.saveApproved(licenseAssignment.getLicense_key(), licenseAssignment.getUser_id()));

    }

    @PostMapping("/add/user")
    public ResponseEntity<LicenseAssignment> addLicenseAssignmentUser (@Valid @RequestBody LicenseAssignment licenseAssignment)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                licenseAssignmentService.saveAwaitingApproval(licenseAssignment.getLicense_key(), licenseAssignment.getUser_id()));

    }

    @PutMapping("/update/record")
    public ResponseEntity<LicenseAssignment> updateLicenseAssignment (@Valid @RequestBody LicenseAssignment licenseAssignment)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                licenseAssignmentService.update(licenseAssignment));
    }

    @PutMapping("/update/{assignmentID}")
    public ResponseEntity<LicenseAssignment> updateLicenseAssignmentApproval (@PathVariable String assignmentID)
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                licenseAssignmentService.approval(assignmentID));
    }

    @DeleteMapping("/{assignmentID}")
    public ResponseEntity<Void> deleteByAssignmentID(@PathVariable String assignmentID)
    {
         licenseAssignmentService.delete(assignmentID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assignmentID}")
    public ResponseEntity<LicenseAssignment> getAssignmentByID (@PathVariable String assignmentID) throws NotFoundException
    {
        return ResponseEntity.ok().body(licenseAssignmentService.getAssignmentByUUID(assignmentID));
    }

    @GetMapping("/set/{assignmentID}")
    public ResponseEntity<Set<LicenseAssignment>> getAssignmentByIDAsIterable (@PathVariable String assignmentID) throws NotFoundException
    {
        return ResponseEntity.ok().body(licenseAssignmentService.getAssignmentAsIterable(assignmentID));
    }

    @GetMapping()
    public ResponseEntity<Set<LicenseAssignment>> getAllAssignments()
    {
        return ResponseEntity.ok(licenseAssignmentService.getAllAssignments());
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<Set<LicenseAssignment>> getAllAssignmentsByUser(@PathVariable String userID)
    {
        return ResponseEntity.ok(licenseAssignmentService.getAllAssignmentsByUser(userID));
    }

    @GetMapping("/approve")
    public ResponseEntity<Set<LicenseAssignment>> getAllAssignmentsNotApproved()
    {
        return ResponseEntity.ok(licenseAssignmentService.getAllAssignmentsNotApproved());
    }

}
