package org.eventticket.eventticketapp.data.repository;

import org.eventticket.eventticketapp.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional <Event> findEventByEventName(String eventName);
    Optional <Event> findEventByEventId(int Id);
    Event findEventByEventLocation(String eventLocation);
//    Optional <Event> findEventByDateAndTime(String eventDateAndTime);
//    Optional <Event> findEventByPrice(String eventPrice);

}
