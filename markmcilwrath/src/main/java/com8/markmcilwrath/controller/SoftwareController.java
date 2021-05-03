package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.service.SoftwareService;
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
@RequestMapping(value = "/software")
public class SoftwareController
{
    private SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    @PostMapping("/add/{vendorId}")
    public ResponseEntity<Software> addSoftware(@PathVariable String vendorId,
                                                @Valid @RequestBody Software software)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                softwareService.save(software.getName(), software.getVersion(), vendorId)
        );
    }

    @PutMapping("/update/{vendorId}")
    public ResponseEntity<Software> updateSoftware(@PathVariable String vendorId, @Valid @RequestBody Software software) throws InvocationTargetException, IllegalAccessException
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                softwareService.update(software, vendorId));
    }

    @DeleteMapping("/{softwareId}")
    public ResponseEntity<Void> deleteSoftwareById(@PathVariable String softwareId) {
        softwareService.delete(softwareId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{softwareId}")
    public ResponseEntity<Software> getSoftwareById(@PathVariable String softwareId) throws NotFoundException {
        return ResponseEntity.ok().body(softwareService.getSoftware(softwareId));
    }

    @GetMapping("/vendor/{vendorID}")
    public ResponseEntity<Set<Software>> getSoftwareByVendorID (@PathVariable String vendorID) throws NotFoundException
    {
        return ResponseEntity.ok(softwareService.getAllSoftwaresByVendor(vendorID));
    }

    @GetMapping()
    public ResponseEntity<Set<Software>> getSoftwares() {
        return ResponseEntity.ok(softwareService.getAllSoftwares());
    }
}
