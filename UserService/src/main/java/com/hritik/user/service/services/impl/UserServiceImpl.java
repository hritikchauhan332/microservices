package com.hritik.user.service.services.impl;

import com.hritik.user.service.entities.Hotel;
import com.hritik.user.service.entities.Rating;
import com.hritik.user.service.entities.User;
import com.hritik.user.service.exceptions.ResourceNotFoundException;
import com.hritik.user.service.external.services.HotelService;
import com.hritik.user.service.repositories.UserRepository;
import com.hritik.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();

        for(User user : users)
        {
            Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            logger.info("Data from rating class: " + userRatings.toString());

            List<Rating> ratings = Arrays.stream(userRatings).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {
                ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = responseEntity.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            user.setRatings(ratingList);
        }

        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server !! : " + userId));
        Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.info("Data from rating class: " + userRatings.toString());

        List<Rating> ratings = Arrays.stream(userRatings).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = responseEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
