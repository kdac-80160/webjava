package com.sunbeam.daos;

public interface ShareDao extends AutoCloseable {
	int shareReviews(int id, int reviewId);
	int deleteShare(int reviewId);
}
