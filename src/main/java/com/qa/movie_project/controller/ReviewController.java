package com.qa.movie_project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.movie_project.dto.NewReviewDTO;
import com.qa.movie_project.dto.ReviewDTO;
import com.qa.movie_project.dto.UpdateReviewDTO;
import com.qa.movie_project.service.ReviewService;

@RestController
@RequestMapping(path = "/review")
@CrossOrigin("*")
public class ReviewController {

	private ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getReviews() {
		return ResponseEntity.ok(reviewService.getReviews());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ReviewDTO> getReview(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(reviewService.getReview(id));
	}

	@PostMapping
	public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody NewReviewDTO review) {
		ReviewDTO newReview = reviewService.createReview(review);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/review/" + newReview.getId());

		return new ResponseEntity<>(newReview, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody UpdateReviewDTO review,
			@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(reviewService.updateReview(review, id));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ReviewDTO> deleteReview(@PathVariable(name = "id") int id) {
		ReviewDTO deletedReview = reviewService.getReview(id);
		reviewService.deleteReview(id);
		return ResponseEntity.ok(deletedReview);
	}
}
