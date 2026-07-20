package com.example.dodast.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dodast.Model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndAdvertisementId(Long userId, Long advertisementId);

    Optional<Favorite> findByUserIdAndAdvertisementId(Long userId, Long advertisementId);

    List<Favorite> findByUserId(Long userId);
} 