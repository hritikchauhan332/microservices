package com.hritik.rating.repository;

import com.hritik.rating.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating,String> {
    public List<Rating> findByUserId(String userId);

    public List<Rating> findByHotelId(String hotelId);
}
