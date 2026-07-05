package com.example.dodast.Repository;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Category;
import com.example.dodast.Model.City;
import com.example.dodast.Model.Enums.AdvertisementStatus;
import com.example.dodast.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByOwner(User owner);

    List<Advertisement> findByCategory(Category category);

    List<Advertisement> findByCity(City city);

    List<Advertisement> findByStatus(AdvertisementStatus status);

    List<Advertisement> findByStatusAndCity(AdvertisementStatus status, City city);

    List<Advertisement> findByStatusAndCategory(AdvertisementStatus status, Category category);

}
