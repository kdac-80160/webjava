package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Reviews;

public class ReviewsDaoImpl extends Dao implements ReviewDao {

	public ReviewsDaoImpl() throws Exception {
		
	}

	@Override
	public int insertReview(Reviews review) {
		String sql = "insert into reviews values(default,?,?,?,?,now())";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, review.getMovie_id());
			ps.setString(2, review.getReview());
			ps.setInt(3, review.getRating());
			ps.setInt(4, review.getUser_id());
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int editReview(Reviews review, int id) {
		String sql = "update reviews set rating = ?, review = ?, movie_id = ?, modified = now() where review_id = ? and user_id = ?";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, review.getRating());
			ps.setString(2, review.getReview());
			ps.setInt(3, review.getMovie_id());
			ps.setInt(4, review.getRev_id());
			ps.setInt(5, id);
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Reviews> getReviews() {
		String sql = "select * from reviews";
		List<Reviews> list = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Reviews review = new Reviews();
				review.setRev_id(set.getInt("review_id"));
				review.setMovie_id(set.getInt("movie_id"));
				review.setReview(set.getString("review"));
				review.setRating(set.getInt("rating"));
				review.setUser_id(set.getInt("user_id"));
				java.sql.Timestamp ts = set.getTimestamp("modified");
				review.setModified(ts.toString());
				list.add(review);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reviews> getReviewsById(int id) {
		String sql = "select * from reviews where user_id = ?";
		ArrayList<Reviews> list = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Reviews review = new Reviews();
				review.setRev_id(set.getInt("review_id"));
				review.setMovie_id(set.getInt("movie_id"));
				review.setReview(set.getString("review"));
				review.setRating(set.getInt("rating"));
				review.setUser_id(set.getInt("user_id"));
				java.sql.Timestamp ts = set.getTimestamp("modified");
				review.setModified(ts.toString());
				list.add(review);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reviews> getReviewsByRevId(int id, int userId) {
		String sql = "select * from reviews where review_id = ? and user_id = ?";
		ArrayList<Reviews> list = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ps.setInt(2, userId);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Reviews review = new Reviews();
				review.setRev_id(set.getInt("review_id"));
				review.setMovie_id(set.getInt("movie_id"));
				review.setReview(set.getString("review"));
				review.setRating(set.getInt("rating"));
				review.setUser_id(set.getInt("user_id"));
				java.sql.Timestamp ts = set.getTimestamp("modified");
				review.setModified(ts.toString());
				list.add(review);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reviews> getSharedReviews(int id) {
		String sql = "select reviews.review_id, reviews.movie_id, review, rating, reviews.user_id, modified from reviews,shares where shares.user_id = ? and reviews.review_id = shares.review_id";
		ArrayList<Reviews> list = new ArrayList<>();
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			while(set.next())
			{
				Reviews review = new Reviews();
				review.setRev_id(set.getInt("review_id"));
				review.setMovie_id(set.getInt("movie_id"));
				review.setReview(set.getString("review"));
				review.setRating(set.getInt("rating"));
				review.setUser_id(set.getInt("user_id"));
				java.sql.Timestamp ts = set.getTimestamp("modified");
				review.setModified(ts.toString());
				list.add(review);
			}
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteReview(int id, int reviewId) {
		String sql = "delete from reviews where review_id = ? and user_id = ?";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, reviewId);
			ps.setInt(2, id);
			count = ps.executeUpdate();
			return count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

}
