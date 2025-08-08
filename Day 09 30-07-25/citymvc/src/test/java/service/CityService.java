package service;

 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repo.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<String> getAllCities() {
        return cityRepository.getCities();
    }
}
