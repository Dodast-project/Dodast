package com.example.dodast.Config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.dodast.Model.Category;
import com.example.dodast.Repository.CategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CategoryInitializer implements CommandLineRunner{
    private final CategoryRepository categoryRepository;

    public CategoryInitializer(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        if(categoryRepository.count() > 0) return;
        
        InputStream inputStream = new ClassPathResource("data/categories.json").getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        List<String> data = mapper.readValue(inputStream, new TypeReference<List<String>>(){});

        for(String item: data){

            Category category = Category.builder().name(item).build();

            categoryRepository.save(category);
        }    
    }
}
