package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> listAll();
    Accommodation findById(Long id);
    void deleteById(Long id);
    Accommodation create(String name, Category category, Long hostId, Integer numRooms);
    Accommodation update(Long id, String name, Category category, Long hostId, Integer numRooms);
    void booked(Long id);
    List<Accommodation> filter (Category category);
    void onAccFiltered();
}
