package com.sunbeam.pojos;

public class Reviews {
private int rev_id;
private int movie_id;
private String review;
private int rating;
private int user_id;
private String modified;

public Reviews() {
	
}
public Reviews(int rev_id, int movie_id, String review, int rating, int user_id, String modified) {
	this.rev_id = rev_id;
	this.movie_id = movie_id;
	this.review = review;
	this.rating = rating;
	this.user_id = user_id;
	this.modified = modified;
}

public int getRev_id() {
	return rev_id;
}

public void setRev_id(int rev_id) {
	this.rev_id = rev_id;
}

public int getMovie_id() {
	return movie_id;
}

public void setMovie_id(int movie_id) {
	this.movie_id = movie_id;
}

public String getReview() {
	return review;
}

public void setReview(String review) {
	this.review = review;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public int getUser_id() {
	return user_id;
}

public void setUser_id(int user_id) {
	this.user_id = user_id;
}

public String getModified() {
	return modified;
}

public void setModified(String modified) {
	this.modified = modified;
}

@Override
public String toString() {
	return "Reviews [rev_id=" + rev_id + ", movie_id=" + movie_id + ", review=" + review + ", rating=" + rating
			+ ", user_id=" + user_id + ", modified=" + modified + "]";
}

}
