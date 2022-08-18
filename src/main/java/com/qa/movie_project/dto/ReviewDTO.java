package com.qa.movie_project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ReviewDTO {

	private int id;

	private int rating;

	private String comment;

	private MovieDTO movie;

	private LocalDate postedAt;

	public ReviewDTO() {

	}

	public ReviewDTO(int id, int rating, String comment, MovieDTO movie, LocalDate postedAt) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.movie = movie;
		this.postedAt = postedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDate postedAt) {
		this.postedAt = postedAt;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", rating=" + rating + ", comment=" + comment + ", movie=" + movie
				+ ", postedAt=" + postedAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, id, movie, postedAt, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		return Objects.equals(comment, other.comment) && id == other.id && Objects.equals(movie, other.movie)
				&& Objects.equals(postedAt, other.postedAt) && Objects.equals(rating, other.rating);
	}

}
