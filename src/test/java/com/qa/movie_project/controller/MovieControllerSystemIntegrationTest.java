package com.qa.movie_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.movie_project.dto.MovieDTO;
import com.qa.movie_project.dto.NewMovieDTO;
import com.qa.movie_project.entity.Movie;
import com.qa.movie_project.repo.MovieRepo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({ "test" })
@Sql(scripts = { "classpath:schema.sql",
		"classpath:movie-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class MovieControllerSystemIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MovieRepo movieRepo;

	private List<Movie> savedMovies;
	private List<MovieDTO> savedMovieDTOs = new ArrayList<>();
	private int nextId;
	private String uri;

	@BeforeEach
	public void init() {
		savedMovies = movieRepo.findAll();
		savedMovies.forEach(movie -> savedMovieDTOs.add(modelMapper.map(movie, MovieDTO.class)));
		nextId = savedMovies.get(savedMovies.size() - 1).getId() + 1;
		uri = "/movie";
	}

	@Test
	public void getAllUsersTest() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.request(HttpMethod.GET, uri);

		request.accept(MediaType.APPLICATION_JSON);

		String users = objectMapper.writeValueAsString(savedMovieDTOs);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(users);

		mockMvc.perform(request).andExpectAll(statusMatcher, contentMatcher);
	}

	@Test
	public void createMovieTest() throws Exception {
		NewMovieDTO newMovie = new NewMovieDTO();
		newMovie.setTitle("Avengers");
		newMovie.setGenre("Action");
		newMovie.setReleaseYear(2012);
		newMovie.setRuntime(149);
		MovieDTO expectedMovie = new MovieDTO(nextId, newMovie.getTitle(), newMovie.getGenre(),
				newMovie.getReleaseYear(), newMovie.getRuntime());

		var request = MockMvcRequestBuilders.request(HttpMethod.POST, uri);

		request.accept(MediaType.APPLICATION_JSON);

		request.content(objectMapper.writeValueAsString(newMovie));
		request.contentType(MediaType.APPLICATION_JSON);

		String expectedBody = objectMapper.writeValueAsString(expectedMovie);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher locationMatcher = MockMvcResultMatchers.header().string("Location",
				"http://localhost:8080/movie/" + expectedMovie.getId());
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(expectedBody);

		mockMvc.perform(request).andExpectAll(statusMatcher, locationMatcher, contentMatcher);
	}

}
