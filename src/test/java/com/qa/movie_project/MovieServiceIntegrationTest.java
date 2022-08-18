package com.qa.movie_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.movie_project.dto.MovieDTO;
import com.qa.movie_project.dto.NewMovieDTO;
import com.qa.movie_project.entity.Movie;
import com.qa.movie_project.repo.MovieRepo;
import com.qa.movie_project.service.MovieService;

@SpringBootTest // launch the application context and creates the beans (essentially starts out
				// app with a test configuration)
@Sql(scripts = { "classpath:schema.sql",
		"classpath:movie-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles({ "test" })
public class MovieServiceIntegrationTest {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepo movieRepo; // required to get the test data out of the database

	@Autowired
	private ModelMapper modelMapper;

	private List<Movie> savedMovies;
	private List<MovieDTO> savedMovieDTOs = new ArrayList<>();
	private int nextId;
	private String url;

	@BeforeEach
	public void init() {
		url = "/movie";
		savedMovies = movieRepo.findAll();
		for (Movie movie : savedMovies) {
			savedMovieDTOs.add(modelMapper.map(movie, MovieDTO.class));
		}

		nextId = savedMovies.get(savedMovies.size() - 1).getId() + 1;
	}

	@Test
	public void getAllMoviesTest() {
		assertEquals(savedMovieDTOs, movieService.getMovies());
	}

	@Test
	public void getMovieByIdTest() {
		MovieDTO expected = savedMovieDTOs.get(0);
		MovieDTO actual = movieService.getMovie(expected.getId());
		assertEquals(expected, actual);
	}

	@Test
	public void createMovieTest() {
		NewMovieDTO movie = new NewMovieDTO();
		movie.setTitle("Avengers");
		movie.setGenre("Action");
		movie.setReleaseYear(2012);
		movie.setRuntime(143);

		MovieDTO expected = new MovieDTO(nextId, movie.getTitle(), movie.getGenre(), movie.getReleaseYear(),
				movie.getRuntime());
		MovieDTO actual = movieService.createMovie(movie);

		assertEquals(expected, actual);
	}

	@Test
	public void updateMovieTest() {
		int id = savedMovies.get(0).getId();
		NewMovieDTO movie = new NewMovieDTO();
		movie.setTitle("Avengers");
		movie.setGenre("Action");
		movie.setReleaseYear(2012);
		movie.setRuntime(143);

		MovieDTO expected = new MovieDTO(id, movie.getTitle(), movie.getGenre(), movie.getReleaseYear(),
				movie.getRuntime());
		MovieDTO actual = movieService.updateMovie(movie, id);

		assertEquals(expected, actual);
	}

	@Test
	public void deleteMovieTest() {
		int id = savedMovies.get(0).getId();
		movieService.deleteMovie(id);
		assertEquals(Optional.empty(), movieRepo.findById(id));
	}
}
