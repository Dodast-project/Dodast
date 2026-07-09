package com.example.dodast.Repository;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.AdvertisementImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementImageRepository extends JpaRepository<AdvertisementImage, Long> {

    List<AdvertisementImage> findByAdvertisement(Advertisement advertisement);

}
