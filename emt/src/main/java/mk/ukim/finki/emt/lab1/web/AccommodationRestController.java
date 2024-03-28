package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.dto.AccommodationDto;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationRestController {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationRestController(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addBook(@RequestBody AccommodationDto accommodationDto) {
        if(accommodationDto == null) {
            return ResponseEntity.notFound().build();
        }

        if(hostService.findById(accommodationDto.getHostId()) == null) {
            return ResponseEntity.notFound().build();
        }

        this.accommodationService.create(accommodationDto.getName(), accommodationDto.getCategory(), accommodationDto.getHostId(), accommodationDto.getNumRooms());
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Accommodation> getAccommodations(@RequestParam (required = false) Category category ) {
        if(category != null)
        {
            return this.accommodationService.filter(category);
        }
        return this.accommodationService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
       Accommodation accommodation= this.accommodationService.findById(id);
       if (accommodation == null)
           return ResponseEntity.notFound().build();
       return ResponseEntity.ok().body(accommodation);
    }



    @PostMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.notFound().build();
        }

        if(accommodationService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        this.accommodationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto) {
        if(accommodationDto == null) {
            return ResponseEntity.notFound().build();
        }

        if(hostService.findById(accommodationDto.getHostId()) == null || accommodationService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        this.accommodationService.update(id, accommodationDto.getName(), accommodationDto.getCategory(), accommodationDto.getHostId(), accommodationDto.getNumRooms());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/booked/{id}")
    public ResponseEntity<Void> booked(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.notFound().build();
        }

        if(accommodationService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        this.accommodationService.booked(id);
        return ResponseEntity.ok().build();
    }


}
