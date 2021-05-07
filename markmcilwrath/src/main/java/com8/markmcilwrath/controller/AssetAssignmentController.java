package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.AssetAssignment;
import com8.markmcilwrath.domain.AssetTag;
import com8.markmcilwrath.service.AssetAssignmentService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/asset_assign")
public class AssetAssignmentController {
    private AssetAssignmentService assetAssignmentService;

    public AssetAssignmentController(AssetAssignmentService assetAssignmentService) {
        this.assetAssignmentService = assetAssignmentService;
    }

    @PostMapping("/add/admin")
    public ResponseEntity<AssetAssignment> addAssetAssignmentAdmin (@Valid @RequestBody AssetAssignment assetAssignment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                assetAssignmentService.saveApproved(assetAssignment.getAssetTag(), assetAssignment.getUserID(), assetAssignment.getTags()));
    }

    @PostMapping("/add/user")
    public ResponseEntity<AssetAssignment> addAssetAssignmentUser (@Valid @RequestBody AssetAssignment assetAssignment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                assetAssignmentService.saveAwaitingApproval(assetAssignment.getAssetTag(), assetAssignment.getUserID()));
    }

    @PutMapping("/update/{assignmentID}")
    public ResponseEntity<AssetAssignment> updateAssetAssignmentApproval(@PathVariable String assignmentID) {
        return ResponseEntity.status(HttpStatus.OK).body(
                assetAssignmentService.approval(assignmentID));
    }

    @DeleteMapping("/{assignmentID}")
    public ResponseEntity<Void> deleteByAssignmentID(@PathVariable String assignmentID) {
        assetAssignmentService.delete(assignmentID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assignmentID}")
    public ResponseEntity<AssetAssignment> getAssignmentByID(@PathVariable String assignmentID) throws NotFoundException
    {
        return ResponseEntity.ok().body(assetAssignmentService.getAssignmentByUUID(assignmentID));
    }

    @GetMapping("/set/{assignmentID}")
    public ResponseEntity<Set<AssetAssignment>> getAssignmentByIDAsIterable (@PathVariable String assignmentID) throws NotFoundException
    {
        return ResponseEntity.ok().body(assetAssignmentService.getAssignmentAsIterable(assignmentID));
    }

    @GetMapping()
    public ResponseEntity<Set<AssetAssignment>> getAllAssignments() throws NotFoundException {
        return ResponseEntity.ok().body(assetAssignmentService.getAllAssignments());
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<Set<AssetAssignment>> getAllAssignmentsByUser(@PathVariable String userID) throws NotFoundException {
        return ResponseEntity.ok().body(assetAssignmentService.getAllAssignmentsByUser(userID));
    }

    @GetMapping("/approve")
    public ResponseEntity<Set<AssetAssignment>> getAllAssignmentsNotApproved() throws NotFoundException {
        return ResponseEntity.ok().body(assetAssignmentService.getAllAssignmentsNotApproved());
    }

    @GetMapping("/tags/{assignmentID}")
    public ResponseEntity<Set<AssetTag>> getTagsForAssignment(@PathVariable String assignmentID) throws NotFoundException {
        return ResponseEntity.ok(assetAssignmentService.getTags(assignmentID));
    }
}
