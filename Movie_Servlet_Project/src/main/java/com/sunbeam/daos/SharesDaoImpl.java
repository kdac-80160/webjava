package com.sunbeam.daos;

import java.sql.PreparedStatement;

public class SharesDaoImpl extends Dao implements ShareDao {

	public SharesDaoImpl() throws Exception {
		
	}

	@Override
	public int shareReviews(int id, int reviewId) {
		String sql = "insert into shares values(?,?)";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, reviewId);
			ps.setInt(2, id);
			count = ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteShare(int reviewId) {
		String sql = "delete from shares where review_id = ?";
		int count = 0;
		try(PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, reviewId);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
