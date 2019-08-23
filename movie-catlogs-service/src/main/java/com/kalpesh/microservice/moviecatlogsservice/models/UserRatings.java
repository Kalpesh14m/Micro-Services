package com.kalpesh.microservice.moviecatlogsservice.models;

import java.util.List;

public class UserRatings {
	private List<Rating> userRatings;

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}

}
