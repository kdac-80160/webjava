package beans;

import java.util.List;

import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImpl;

import pojos.Team;

public class TeamBean {
	private TeamDao teamDao;

	public TeamBean() {
		this.teamDao = new TeamDaoImpl();
	}
	
	public List<Team> getAllTeams()
	{
		return teamDao.getTeamIdAndAbr();
	}
	
}
