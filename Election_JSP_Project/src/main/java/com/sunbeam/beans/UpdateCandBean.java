package com.sunbeam.beans;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class UpdateCandBean {
	private int candId;
	private String name;
	private String party;
	private int votes;
	private String message;
	
	public UpdateCandBean() {
	
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCandId() {
		return candId;
	}
	public void setCandId(int candId) {
		this.candId = candId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public void updateCandidate()
	{
		try(CandidateDaoImpl dao = new CandidateDaoImpl())
		{
			Candidate c = new Candidate(candId, name, party, votes);
			int count = dao.update(c);
			this.message = "Candidate updated : " + count;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
