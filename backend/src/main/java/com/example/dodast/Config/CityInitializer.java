package com.example.dodast.Config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.dodast.DTO.InitialData.ProvinceData;
import com.example.dodast.Model.City;
import com.example.dodast.Model.Province;
import com.example.dodast.Repository.CityRepository;
import com.example.dodast.Repository.ProvinceRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CityInitializer implements CommandLineRunner{
    private final ProvinceRepository provinceRepository;
    private final CityRepository cityRepository;

    public CityInitializer(ProvinceRepository provinceRepository, CityRepository cityRepository){
        this.cityRepository = cityRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        if(provinceRepository.count() > 0) return;

        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = new ClassPathResource("data/iran-cities.json").getInputStream();

        List<ProvinceData> data = mapper.readValue(inputStream, new TypeReference<List<ProvinceData>>(){});

        for(ProvinceData item: data){
            Province province = Province.builder().name(item.getProvince()).build();

            provinceRepository.save(province);

            for(String cityName: item.getCities()){

                City city = City.builder().name(cityName).province(province).build();

                cityRepository.save(city);
            }
        }
    }
}