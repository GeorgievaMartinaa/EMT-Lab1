package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.repository.CountryRepository;
import mk.ukim.finki.emt.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public Country create(String name, String continent) {
        return this.countryRepository.save(new Country(name,continent));
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country country = this.findById(id);
        country.setName(name);
        country.setContinent(continent);
        return this.countryRepository.save(country);
    }
}
