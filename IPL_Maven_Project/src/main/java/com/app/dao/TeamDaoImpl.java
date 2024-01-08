package com.app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Team;

import static utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

public class TeamDaoImpl implements TeamDao {

	@Override
	public String addTeam(Team team) {
		String msg = "Adding the team failed!";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Serializable teamId = session.save(team);
			tx.commit();
			msg = "Added team with ID : " + teamId;
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}
		return msg;
	}

	@Override
	public List<Team> getTeamIdAndAbr() {
		List<Team> list = null;
		// 1. get session from SF
		String jpql = "select new pojos.Team(id, abbreviation) from Team t";
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			list = session.createQuery(jpql, Team.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return list;
	}

	@Override
	public Team getTeamById(Integer id) {
		Team team = null; // team does not exist
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			team = session.get(Team.class, id); // null or persistent team object
			tx.commit();
		} catch (Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		return team;
	}

}
