package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class EditCandBean {
	private int candId;
	private Candidate candidate;
	
	public EditCandBean() {
		
	}

	public int getCandId() {
		return candId;
	}

	public void setCandId(int candId) {
		this.candId = candId;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	public void findCandidate()
	{
		try(CandidateDaoImpl dao = new CandidateDaoImpl())
		{
			this.candidate = dao.findById(candId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
