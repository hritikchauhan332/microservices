package com.hritik.hotel.controllers;

import com.hritik.hotel.entities.Hotel;
import com.hritik.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel)
    {
        Hotel savedHotel = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable String hotelId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> findAllHotels()
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }
}
