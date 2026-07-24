package com.example.dodast.Service;

import org.springframework.stereotype.Service;

import com.example.dodast.DTO.Admin.AdminDashboardResponse;
import com.example.dodast.Model.Enums.AdvertisementStatus;
import com.example.dodast.Repository.AdvertisementRepository;
import com.example.dodast.Repository.UserRepository;

@Service
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    public AdminDashboardService(
            UserRepository userRepository,
            AdvertisementRepository advertisementRepository) {

        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public AdminDashboardResponse getStatistics() {

        long userCount = userRepository.count();

        long advertisementCount = advertisementRepository.count();

        long pendingAdvertisementCount = advertisementRepository.countByStatus(AdvertisementStatus.PENDING);

        return new AdminDashboardResponse(
                userCount,
                advertisementCount,
                pendingAdvertisementCount
            );
    }
}