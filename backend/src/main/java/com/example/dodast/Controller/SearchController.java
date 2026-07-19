package com.example.dodast.Controller;



import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.AdSearchRequest;
import com.example.dodast.Service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Validated
public class SearchController {
    
    private final SearchService searchService;

    @GetMapping
    public List<AdvertisementResponse> searchAdvertisements(@Valid @ModelAttribute AdSearchRequest request) {
        return searchService.search(request);
    }
    
}
