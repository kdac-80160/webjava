package com.sunbeam.beans;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

public class ResultsBean {
	private List<Candidate> list;
	
	public ResultsBean() {
		this.list = new ArrayList<Candidate>();
	}

	public List<Candidate> getList() {
		return list;
	}

	public void setList(List<Candidate> list) {
		this.list = list;
	}
	
	public void getCandidates()
	{
		try(CandidateDaoImpl dao = new CandidateDaoImpl())
		{
			this.list = dao.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
