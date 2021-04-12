package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.HardwareService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping(value = "/hardware")

public class HardwareController {

    private HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @PostMapping("/add/{vendorId}")
    public ResponseEntity<Hardware> addHardware(@PathVariable String vendorId, @Valid @RequestBody Hardware hardware)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                hardwareService.save(hardware.getName(), hardware.getModel(), vendorId)
        );
    }

    @PutMapping("/update/{vendorId}")
    public ResponseEntity<Hardware> updateHardware(@PathVariable String vendorId, @Valid @RequestBody Hardware hardware) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(
                hardwareService.update(hardware, vendorId));
    }

    @DeleteMapping("/{hardwareId}")
    public ResponseEntity<Void> deleteHardwareById(@PathVariable String hardwareId) {
        hardwareService.delete(hardwareId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{hardwareId}")
    public ResponseEntity<Hardware> getHardwareById(@PathVariable String hardwareId) throws NotFoundException {
        return ResponseEntity.ok().body(hardwareService.getHardware(hardwareId));
    }

    @GetMapping()
    public ResponseEntity<Set<Hardware>> getHardwares() {
        return ResponseEntity.ok(hardwareService.getAllHardware());
    }
}
