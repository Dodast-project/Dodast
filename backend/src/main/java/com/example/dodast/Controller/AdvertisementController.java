package com.example.dodast.Controller;

import com.example.dodast.Service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.CreateAdvertisementRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping
    public AdvertisementResponse createAdvertisement(
            @Valid @RequestBody CreateAdvertisementRequest request) {

        return advertisementService.createAdvertisement(request);
    }

    @PutMapping("/{id}")
    public AdvertisementResponse updateAdvertisement(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAdvertisementRequest request) {

        return advertisementService.updateAdvertisement(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAdvertisement(@PathVariable Long id) {

        advertisementService.deleteAdvertisement(id);
    }

    @PatchMapping("/{id}/approve")
    public void approveAdvertisement(@PathVariable Long id) {

        advertisementService.approveAdvertisement(id);
    }

    @PatchMapping("/{id}/reject")
    public void rejectAdvertisement(@PathVariable Long id) {

        advertisementService.rejectAdvertisement(id);
    }

    @PatchMapping("/{id}/sold")
    public void markAsSold(@PathVariable Long id) {

        advertisementService.markAsSold(id);
    }

    @GetMapping("/pending")
    public List<AdvertisementResponse> getPendingAdvertisements() {
        return advertisementService.getPendingAdvertisements();
    }
    

}
