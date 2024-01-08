package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class VoteBean {
	private int candId;
	private int userId;
	private String message;

	public VoteBean() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCandId() {
		return candId;
	}

	public void setCandId(int candId) {
		this.candId = candId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void registerVote() {
		try (UserDaoImpl userDao = new UserDaoImpl()) {
			User user = userDao.findById(userId);
			if (user.getStatus() == 1) {
				this.message = "You have already voted.";
				return;
			}
			try (CandidateDaoImpl candDao = new CandidateDaoImpl()) {
				int count = candDao.incrementVote(candId);
				if (count == 1) {
					this.message = "You have voted successfully.";
					userDao.updateStatus(userId, true);
				} else
					this.message = "Voting failed! Some error has occured.";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
