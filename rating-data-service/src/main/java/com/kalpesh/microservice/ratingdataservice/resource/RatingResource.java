package com.kalpesh.microservice.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpesh.microservice.ratingdataservice.models.Rating;
import com.kalpesh.microservice.ratingdataservice.models.UserRatings;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("users/{userId}")
	public UserRatings getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("1234", 2), new Rating("5678", 4), new Rating("2232", 3));
		UserRatings userRatings = new UserRatings();
		userRatings.setUserRatings(ratings);
		return userRatings;
	}

}
