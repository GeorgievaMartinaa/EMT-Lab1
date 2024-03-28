package mk.ukim.finki.emt.lab1.Listener;

import mk.ukim.finki.emt.lab1.events.FilterEvent;
import mk.ukim.finki.emt.lab1.service.AccommodationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccomodationListener {
    private final AccommodationService accommodationService;

    public AccomodationListener(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @EventListener
    public void onAccFiltered(FilterEvent event){
        this.accommodationService.onAccFiltered();
    }
}
