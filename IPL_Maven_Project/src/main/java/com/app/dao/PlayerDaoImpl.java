package com.app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Player;
import pojos.Team;
import utils.HibernateUtils;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public String addPlayer(Player player, Integer teamId) {
		String msg = "Adding player failed.";
		Session session = HibernateUtils.getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Team team = session.get(Team.class, teamId);
			if(team!=null)
			{
				team.addPlayer(player);
				msg = "Player added successfully.";
			}
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		return msg;
	}

}
