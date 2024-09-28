package org.eventticket.eventticketapp.controllers;

import lombok.RequiredArgsConstructor;
import org.eventticket.eventticketapp.data.model.Event;
import org.eventticket.eventticketapp.dto.request.BookEventRequest;
import org.eventticket.eventticketapp.dto.request.FindEventByLocationRequest;
import org.eventticket.eventticketapp.dto.request.FindEventByNameRequest;
import org.eventticket.eventticketapp.dto.request.FindEventByPriceRequest;
import org.eventticket.eventticketapp.dto.response.BookEventResponse;
import org.eventticket.eventticketapp.dto.response.FindEventByLocationResponse;
import org.eventticket.eventticketapp.dto.response.FindEventByNameResponse;
import org.eventticket.eventticketapp.dto.response.FindEventByPriceResponse;
import org.eventticket.eventticketapp.services.AdminServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class EventControllers {

    private final AdminServices adminServices;

    @PostMapping("/new_event")
    public ResponseEntity<?> createNewEvent(@RequestBody BookEventRequest request){
        try{
            BookEventResponse response = adminServices.createEvent(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find_location/{eventLocation}")
    public ResponseEntity<?> findEventByLocation(@PathVariable String eventLocation){

        try{
            List<Event> response = adminServices.findEventByEventLocation(eventLocation);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find_by_name/{eventName}")
    public ResponseEntity<?> findByName(@PathVariable ("eventName") String eventName){
        try{
            Event response = adminServices.findEventByName(eventName);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find_by_price/{price}")
    public ResponseEntity<?> findEventByPrice(@PathVariable String price){
        try{
            FindEventByPriceResponse response = (FindEventByPriceResponse) adminServices.findEventByPrice(price);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
