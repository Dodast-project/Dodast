package com.example.dodast.Repository;

import com.example.dodast.Model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Optional<Province> findByName(String name);
    Optional<Province> findById(Long id);

}
