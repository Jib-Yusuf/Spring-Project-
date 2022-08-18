package com.qa.movie_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.movie_project.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
