package mk.ukim.finki.emt.lab1.events;

import org.springframework.context.ApplicationEvent;

public class FilterEvent extends ApplicationEvent {

    public FilterEvent(Object source) {
        super(source);
    }
}
