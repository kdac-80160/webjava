package beans;

import java.time.LocalDate;
import java.time.Period;

import com.app.dao.PlayerDaoImpl;
import com.app.dao.TeamDaoImpl;

import pojos.Player;
import pojos.Team;

public class AddPlayerBean {
	private Integer myTeam;
	private String fn;
	private String ln;
	private String dob;
	private double avg;
	private int wickets;
	private PlayerDaoImpl pdao;
	private TeamDaoImpl tdao;
	
	public AddPlayerBean() {
		pdao = new PlayerDaoImpl();
		tdao = new TeamDaoImpl();
	}

	public Integer getMyTeam() {
		return myTeam;
	}

	public void setMyTeam(Integer myTeam) {
		this.myTeam = myTeam;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public PlayerDaoImpl getPdao() {
		return pdao;
	}

	public void setPdao(PlayerDaoImpl pdao) {
		this.pdao = pdao;
	}

	public TeamDaoImpl getTdao() {
		return tdao;
	}

	public void setTdao(TeamDaoImpl tdao) {
		this.tdao = tdao;
	}
	
	public String validateAndAddPlayer()
	{
		String msg = "Adding player failed.";
		Team team = tdao.getTeamById(myTeam);
		if(team!=null)
		{
			if(team.getMaxAge() < Period.between(LocalDate.parse(dob), LocalDate.now()).getYears())
			{
				msg = "Player age exceeds the required age.";
			}
			else if(Double.compare(team.getBattingAvg(), avg) > 0)
			{
				msg = "Player's batting avg is lower than required avg.";
			}
			else if(team.getWicketsTaken() > this.wickets)
			{
				msg = "Wicket taken by player are lesser than the required criteria.";
			}
			else
			{
				Player player = new Player(fn, ln, LocalDate.parse(dob), avg, wickets);
				msg = pdao.addPlayer(player, myTeam);
			}
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return "AddPlayerBean [myTeam=" + myTeam + ", fn=" + fn + ", ln=" + ln + ", dob=" + dob + ", avg=" + avg
				+ ", wickets=" + wickets + "]";
	}
	
	
	
}
