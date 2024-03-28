package mk.ukim.finki.emt.lab1.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;

@Data
public class AccommodationDto {
    String name;
    Category category;
    Long hostId;
    Integer numRooms;
}
