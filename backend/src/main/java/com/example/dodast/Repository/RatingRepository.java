package com.example.dodast.Repository;

import com.example.dodast.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByBuyerIdAndAdvertisementId(Long buyerId, Long advertisementId);

    List<Rating> findBySellerIdOrderByCreatedAtDesc(Long sellerId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.seller.id = :sellerId")
    Double findAverageScoreBySellerId(@Param("sellerId") Long sellerId);

    long countBySellerId(Long sellerId);
}
