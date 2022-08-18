package com.qa.movie_project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.movie_project.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

	List<Review> findByMovieId(int id);

}
