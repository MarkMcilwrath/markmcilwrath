package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Asset;
import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.service.AssetService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping(value = "/asset")
public class AssetController
{
    private AssetService assetService;

    public AssetController(AssetService assetService)
    {
        this.assetService = assetService;
    }

    @PostMapping("/add/{hardwareID}")
    public ResponseEntity<Asset> addAsset (@PathVariable String hardwareID, @Valid @RequestBody Asset asset)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                assetService.save(asset.getSerialNumber(), asset.getPurchaseDate(), hardwareID)
        );
    }

    @PutMapping("/update/{hardwareID}")
    public ResponseEntity<Asset> updateAsset(@PathVariable String hardwareID, @Valid @RequestBody Asset asset) throws
            InvocationTargetException, IllegalAccessException
    {
        return ResponseEntity.status(HttpStatus.OK).body(
                assetService.update((asset), hardwareID));
    }

    @DeleteMapping("/{assetTag}")
    public ResponseEntity<Void> deleteAssetByAssetTag(@PathVariable String assetTag)
    {
        assetService.delete(assetTag);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assetTag}")
    public ResponseEntity<Asset> getAssetByAssetTag(@PathVariable String assetTag) throws NotFoundException
    {
        return ResponseEntity.ok().body(assetService.getAssetbyAssetTag(assetTag));
    }

    @GetMapping("/hardware/{hardwareID}")
    public ResponseEntity<Set<Asset>> getAllAssetsByHardware(@PathVariable String hardwareID)
    {
        return ResponseEntity.ok(assetService.getAllAssetForHardwareID(hardwareID));
    }

    @GetMapping()
    public ResponseEntity<Set<Asset>> getAllAssets()
    {
        return ResponseEntity.ok(assetService.getAllAsset());
    }

    @GetMapping("/free")
    public ResponseEntity<Set<Asset>> getAllFreeAssets()
    {
        return ResponseEntity.ok(assetService.getAllFreeAsset());
    }

}
