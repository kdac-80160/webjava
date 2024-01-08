package com.sunbeam.daos;

import java.util.List;

import com.sunbeam.pojos.Reviews;

public interface ReviewDao extends AutoCloseable {
	int insertReview(Reviews review);
	int editReview(Reviews review, int id);
	List<Reviews> getReviews();
	List<Reviews> getReviewsById(int id);
	List<Reviews> getReviewsByRevId(int id, int userId);
	List<Reviews> getSharedReviews(int id);
	int deleteReview(int id, int reviewId);
}
