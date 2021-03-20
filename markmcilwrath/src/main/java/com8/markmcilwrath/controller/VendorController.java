package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Vendor;
import com8.markmcilwrath.service.VendorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController {

    private VendorService vendorService;
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/add")
    public ResponseEntity<Vendor> addVendor(@Valid @RequestBody Vendor vendor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                vendorService.save(vendor.getName()));
    }


    @DeleteMapping("/{vendorId}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String vendorId) {
        vendorService.delete(vendorId);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/{name}")
//    public ResponseEntity<Void> deleteVendorByName(@PathVariable String name) {
//        vendorService.deleteVendorByName(name);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<Vendor> getVendor(@PathVariable String vendorId) throws NotFoundException {
        return ResponseEntity.ok(vendorService.getVendor(vendorId));
    }

//    @GetMapping("/{vendorName}")
//    public ResponseEntity<Vendor> getVendorByName(@PathVariable String vendorName) throws NotFoundException {
//        return ResponseEntity.ok(vendorService.getVendorByName(vendorName));
//    }

    @GetMapping()
    public ResponseEntity<Set<Vendor>> getVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
}