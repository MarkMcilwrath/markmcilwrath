package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Vendor;
import com8.markmcilwrath.service.VendorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController {

    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/{vendorName}")
    public ResponseEntity<Vendor> addVendor(@PathVariable String vendorName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.save(vendorName));
    }


    @DeleteMapping("/{vendorId}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String vendorId) {
        vendorService.delete(vendorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<Vendor> getVendor(@PathVariable String vendorId) throws NotFoundException {
        return ResponseEntity.ok(vendorService.getVendor(vendorId));
    }

    @GetMapping()
    public ResponseEntity<Set<Vendor>> getVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
}