package com.example.dodast.Repository;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Category;
import com.example.dodast.Model.City;
import com.example.dodast.Model.Enums.AdvertisementStatus;
import com.example.dodast.Model.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByOwner(User owner);

    List<Advertisement> findByCategory(Category category);

    List<Advertisement> findByCity(City city);

    List<Advertisement> findByStatus(AdvertisementStatus status);

    List<Advertisement> findByStatusAndCity(AdvertisementStatus status, City city);

    List<Advertisement> findByStatusAndCategory(AdvertisementStatus status, Category category);

    @Query("""
            SELECT advert
            FROM Advertisement advert
            WHERE advert.status = :status
            AND (
                :keyword IS NULL 
                OR LOWER(advert.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(advert.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
            AND (
                :categoryId IS NULL
                OR a.category.id = :categoryId
              )
            AND (
                :provinceId IS NULL
                OR a.province.id = :provinceId
                )
            AND (
                :cityId IS NULL
                OR a.city.id = :cityId
                )
            AND (
                :minPrice IS NULL
                OR a.price >= :minPrice
                )
            AND (
                :maxPrice IS NULL
                OR a.price <= :maxPrice
                )
    """)
    List<Advertisement> searchAdvertisements(
        @Param("status")
        AdvertisementStatus status,
        @Param("keyword")
        String keyword,
        @Param("categoryId")
        Long categoryId,
        @Param("provinceId")
        Long provinceId,
        @Param("cityId")
        Long cityId,
        @Param("minPrice")
        Long minPrice,
        @Param("maxPrice")
        Long maxPrice,
        Sort sort
    );
}
