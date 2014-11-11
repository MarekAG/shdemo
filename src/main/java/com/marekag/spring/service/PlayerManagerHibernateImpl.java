package com.marekag.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marekag.spring.domain.Player;


@Component
@Transactional
public class PlayerManagerHibernateImpl implements PlayerManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		player.setId(null);
		sessionFactory.getCurrentSession().persist(player);

	}

	@Override
	public Player getPlayer(Player player) {
		// TODO Auto-generated method stub
		return getPlayer(player.getId());
	}

	@Override
	public Player getPlayer(Long id) {
		// TODO Auto-generated method stub
		return(Player) sessionFactory.getCurrentSession().get(Player.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		List<Player> players = new ArrayList<Player>();
		for(Object o : sessionFactory.getCurrentSession().getNamedQuery("player.all").list()) {
			players.add((Player) o);
		}
		return sessionFactory.getCurrentSession().getNamedQuery("player.all").list();
	//	return players;
	}

	@Override
	public void updatePlayer(Player player, String position) {
		// TODO Auto-generated method stub
		Player p = (Player) sessionFactory.getCurrentSession().get(Player.class, player.getId());
		p.setPosition(position);
	}

	@Override
	public void deletePlayer(Player player) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(player);

	}

	@Override
	public void deletePlayer(Long id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(getPlayer(id));

	}

	@Override
	public void deleteAllPlayers() {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().getNamedQuery("player.deleteAll").executeUpdate();

	}

}
