package com.hritik.hotel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaffMembers() {
        List<String> list = Arrays.asList("Gunther Steiner", "Christian Horner", "Toto Wolff", "Zack Brown");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
