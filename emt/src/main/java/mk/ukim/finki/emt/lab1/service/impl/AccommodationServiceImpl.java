package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.events.FilterEvent;
import mk.ukim.finki.emt.lab1.model.Accommodation;
import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;
import mk.ukim.finki.emt.lab1.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab1.repository.HostRepository;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation create(String name, Category category, Long hostId, Integer numRooms) {
        Host host= hostRepository.findById(hostId).orElseThrow(InvalidParameterException::new);
        return this.accommodationRepository.save(new Accommodation(name,category,host,numRooms));
    }


    @Override
    public Accommodation update(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Host host= hostRepository.findById(hostId).orElseThrow(InvalidParameterException::new);
        Accommodation accommodation = this.findById(id);

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(host);
        accommodation.setNumRooms(numRooms);

        return this.accommodationRepository.save(accommodation);
    }

    @Override
    public void booked(Long id) {
        Accommodation accommodation = this.findById(id);
        accommodation.setNumRooms(accommodation.getNumRooms() - 1);

        accommodationRepository.save(accommodation);

    }

    @Override
    public List<Accommodation> filter(Category category) {
        List<Accommodation> accommodations = this.listAll();
        List<Accommodation> accommodationList =new ArrayList<>();

        for (Accommodation a:accommodations) {
            if(a.getCategory() == category)
                accommodationList.add(a);
        }
        if(accommodationList.isEmpty())
            this.applicationEventPublisher.publishEvent(new FilterEvent(category));

        return accommodationList;
    }

    @Override
    public void onAccFiltered() {
        System.out.println("No accomodation filtered");

    }
}
