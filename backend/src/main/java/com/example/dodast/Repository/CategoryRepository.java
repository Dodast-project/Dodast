package com.example.dodast.Repository;

import com.example.dodast.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
    Optional<Category> findById(Long id);
}
