package com.qa.movie_project.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Min(1)
	@Max(10)
	private int rating;

	@Size(min = 0, max = 250, message = "The comments must contain a minimum of 10 characters and maximum of 250 characters")
	private String comment;

	@NotNull
	private LocalDate postedAt;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;

	public Review() {
		super();
		this.postedAt = LocalDate.now();
	}

	public Review(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.postedAt = LocalDate.now();
	}

	public Review(int id, int rating, String comment) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.postedAt = LocalDate.now();
	}

	public Review(int rating, String comment, Movie movie) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.movie = movie;
		this.postedAt = LocalDate.now();
	}

	public Review(int id, int rating, String comment, Movie movie) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.postedAt = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", comment=" + comment + ", postedAt=" + postedAt
				+ ", movie=" + movie + "]";
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
		Review other = (Review) obj;
		return Objects.equals(comment, other.comment) && id == other.id && Objects.equals(movie, other.movie)
				&& Objects.equals(postedAt, other.postedAt) && Objects.equals(rating, other.rating);
	}

}
