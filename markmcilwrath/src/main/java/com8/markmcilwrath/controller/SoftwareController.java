//package com8.markmcilwrath.controller;
//
//import com8.markmcilwrath.domain.Software;
//import com8.markmcilwrath.domain.User;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping(value = "/software")
//public class SoftwareController {
//
//
//    @PostMapping
//    public User addSoftware(@RequestBody Software software) {
//        return ResponseEntity.created(software);
//    }
//
//
//    @DeleteMapping("/{softwareId}")
//    public User deleteSoftware(@PathVariable String softwareId) {
//        return ResponseEntity.ok();
//    }
//
//    @GetMapping("/{softwareId}")
//    public User getSoftware(@PathVariable String softwareId) {
//        return ResponseEntity.ok();
//    }
//
//    @GetMapping()
//    public User getSoftware() {
//        return ResponseEntity.ok();
//    }
//}