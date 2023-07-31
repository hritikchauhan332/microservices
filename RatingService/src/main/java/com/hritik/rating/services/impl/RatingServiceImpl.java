package com.hritik.rating.services.impl;

import com.hritik.rating.entities.Rating;
import com.hritik.rating.repository.RatingRepo;
import com.hritik.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo repo;

    @Override
    public Rating create(Rating rating) {
        return repo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repo.findByHotelId(hotelId);
    }
}
