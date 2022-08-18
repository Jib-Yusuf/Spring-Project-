package com.qa.movie_project.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MovieDTO {

	private int id;

	@NotNull
	@NotBlank
	private String title;

	@NotNull
	private String genre;

	@NotNull
	private Integer releaseYear;

	@NotNull
	private int runtime;

	public MovieDTO() {
		super();
	}

	public MovieDTO(int id, String title, String genre, Integer releaseYear, int runtime) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.runtime = runtime;
	}

	public MovieDTO(String title, String genre, Integer releaseYear, int runtime) {
		super();
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.runtime = runtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", genre=" + genre + ", releaseYear=" + releaseYear
				+ ", runtime=" + runtime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(genre, id, releaseYear, runtime, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDTO other = (MovieDTO) obj;
		return Objects.equals(genre, other.genre) && id == other.id && Objects.equals(releaseYear, other.releaseYear)
				&& runtime == other.runtime && Objects.equals(title, other.title);
	}

}
