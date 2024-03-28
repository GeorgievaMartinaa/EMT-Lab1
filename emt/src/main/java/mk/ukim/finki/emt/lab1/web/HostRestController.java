package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.model.dto.HostDto;
import mk.ukim.finki.emt.lab1.service.CountryService;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostRestController {
    private final HostService hostService;
    private final CountryService countryService;

    public HostRestController(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addHost(@RequestBody HostDto hostDto) {
        if(hostDto == null) {
            return ResponseEntity.notFound().build();
        }
        if(countryService.findById(hostDto.getCountryId()) == null) {
            return ResponseEntity.notFound().build();
        }

        this.hostService.create(hostDto.getName(), hostDto.getSurname(), hostDto.getCountryId());
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Host> getHosts() {
        return this.hostService.listAll();
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.hostService.deleteById(id);
        if (this.hostService.findById(id) == null) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
