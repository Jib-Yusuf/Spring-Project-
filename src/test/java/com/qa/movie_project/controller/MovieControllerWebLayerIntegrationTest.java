package com.qa.movie_project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.movie_project.dto.MovieDTO;
import com.qa.movie_project.dto.NewMovieDTO;
import com.qa.movie_project.service.MovieService;

@WebMvcTest(MovieController.class)
@ActiveProfiles({ "test" })
public class MovieControllerWebLayerIntegrationTest {

	@MockBean // equivalent to Mockitos @Mock
	private MovieService movieService;

	@Autowired
	private MovieController movieController;

	private List<MovieDTO> movieDTOs;

	@BeforeEach
	public void init() {
		movieDTOs = List.of(new MovieDTO(1, "Interstellar", "Thriller", 2014, 169),
				new MovieDTO(2, "Toy Story", "Animation", 1995, 81));
	}

	@Test
	public void getAllMoviesTest() {
		// arrange
		ResponseEntity<?> expected = ResponseEntity.ok(movieDTOs);
		when(movieService.getMovies()).thenReturn(movieDTOs);

		// act
		ResponseEntity<?> actual = movieController.getMovies();

		// assert
		assertEquals(expected, actual);
		verify(movieService).getMovies();
	}

	@Test
	public void createMovieTest() {
		// arrange
		NewMovieDTO newMovie = new NewMovieDTO();
		newMovie.setTitle("Interstellar");
		newMovie.setGenre("Thriller");
		newMovie.setReleaseYear(2018);
		newMovie.setRuntime(150);

		MovieDTO expectedMovie = new MovieDTO(1, newMovie.getTitle(), newMovie.getGenre(), newMovie.getReleaseYear(),
				newMovie.getRuntime());
		ResponseEntity<?> expected = ResponseEntity
				.created(URI.create("http://localhost:8080/movie/" + expectedMovie.getId())).body(expectedMovie);

		when(movieService.createMovie(newMovie)).thenReturn(expectedMovie);

		// act
		ResponseEntity<?> actual = movieController.createMovie(newMovie);

		// assert
		assertEquals(expected, actual);
		verify(movieService).createMovie(newMovie);
	}

}
