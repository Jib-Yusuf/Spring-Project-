package com.qa.movie_project.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateReviewDTO {

	@NotNull
	@Min(1)
	@Max(10)
	private int rating;

	@Size(min = 0, max = 250, message = "The comments must contain a minimum of 10 characters and maximum of 250 characters")
	private String comment;

	public UpdateReviewDTO(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
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

	@Override
	public String toString() {
		return "UpdateReviewDTO [rating=" + rating + ", comment=" + comment + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateReviewDTO other = (UpdateReviewDTO) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(rating, other.rating);
	}

}
