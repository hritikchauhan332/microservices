package com.hritik.rating.services;

import com.hritik.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);
}
