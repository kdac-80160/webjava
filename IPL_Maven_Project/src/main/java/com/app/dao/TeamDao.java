package com.app.dao;

import java.util.List;

import pojos.Team;

public interface TeamDao {
	String addTeam(Team team);
	public List<Team> getTeamIdAndAbr();
	Team getTeamById(Integer id);
}
