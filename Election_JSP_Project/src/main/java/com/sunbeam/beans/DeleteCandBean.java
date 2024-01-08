package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDaoImpl;

public class DeleteCandBean {
	private int candId;
	private String message;

	public DeleteCandBean() {
		
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
	
	public void delCandidate()
	{
		try(CandidateDaoImpl dao = new CandidateDaoImpl())
		{
			int count = dao.deleteById(candId);
			this.message = "Candidates deleted : " + count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
