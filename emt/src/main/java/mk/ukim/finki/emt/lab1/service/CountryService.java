package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> listAll();
    Country findById(Long id);
    void deleteById(Long id);
    Country create(String name, String continent);
    Country update(Long id, String name, String continent);
}
