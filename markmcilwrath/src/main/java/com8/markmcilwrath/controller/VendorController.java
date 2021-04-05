package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.domain.Vendor;
import com8.markmcilwrath.service.VendorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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

    @PutMapping("/update")
    public ResponseEntity<Vendor> updateVendor(@Valid @RequestBody Vendor vendor) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(
                vendorService.update((vendor)));
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