package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.domain.Vendor;
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

    @PostMapping("/add")
    public ResponseEntity<Software> addSoftware(@Valid @RequestBody Software software)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                softwareService.save(software.getName(), software.getVersion())
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Software> updateSoftware(@Valid @RequestBody Software software) throws InvocationTargetException, IllegalAccessException
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                softwareService.update((software)));
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

    @GetMapping()
    public ResponseEntity<Set<Software>> getSoftwares() {
        return ResponseEntity.ok(softwareService.getAllSoftwares());
    }
}
