package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.dto.CountryDto;
import mk.ukim.finki.emt.lab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCountry(@RequestBody CountryDto countryDto) {
        if(countryDto == null) {
            return ResponseEntity.notFound().build();
        }

        this.countryService.create(countryDto.getName(),countryDto.getContinent());
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Country> getCountries() {
        return this.countryService.listAll();
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if (this.countryService.findById(id) == null) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
