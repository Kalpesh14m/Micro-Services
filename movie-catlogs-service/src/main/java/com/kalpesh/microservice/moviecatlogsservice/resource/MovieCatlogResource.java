package com.kalpesh.microservice.moviecatlogsservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kalpesh.microservice.moviecatlogsservice.models.CatlogItem;
import com.kalpesh.microservice.moviecatlogsservice.models.Movie;
import com.kalpesh.microservice.moviecatlogsservice.models.UserRatings;
import com.netflix.discovery.DiscoveryClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatlogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatlogItem> getCatlog(@PathVariable("userId") String userId) {

		// User of WebClient to remove RestTemplate:
		// Create bean for builder;
		// WebClient.Builder builder = WebClient.builder();

		// For restTemplate obj we created @Bean and we can autowierd it
		// RestTemplate restTemplate = new RestTemplate();
		// 1.get all rated movies ID
		UserRatings ratings = restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userId,
				UserRatings.class);

		return ratings.getUserRatings().stream().map(rating -> {
			// 2.For eache movie Id, call movie info service and get details
			// Replcae restTemplate by webClientBuilder
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movie.class);
			// .3Put them all together
			return new CatlogItem(movie.getName(), "good", rating.getRating());
		}).collect(Collectors.toList());
		// return Collections.singletonList(new CatlogItem("abc", "good", 4));
	}
}

/*
 * Movie movie =
 * webClientBuilder.build().get().uri("http://localhost:8082/movies/" +
 * rating.getMovieId()) .retrieve().bodyToMono(Movie.class).block();
 */
