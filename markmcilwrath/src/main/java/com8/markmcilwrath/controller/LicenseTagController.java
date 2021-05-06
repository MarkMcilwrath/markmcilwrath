package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseTag;
import com8.markmcilwrath.service.LicenseTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/licenseTag")
public class LicenseTagController
{
    private LicenseTagService licenseTagService;

    public LicenseTagController (LicenseTagService licenseTagService)
    {
        this.licenseTagService=licenseTagService;
    }


}
