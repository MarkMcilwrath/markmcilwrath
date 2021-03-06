package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.service.HardwareService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/hardware")

public class HardwareController {

    private HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService){
        this.hardwareService= hardwareService;
    }

}
