package com.marekag.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marekag.spring.domain.Ball;
import com.marekag.spring.domain.Manufacturer;


@Component
@Transactional
public class BallManagerHibernateImpl implements BallManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addBall(Ball ball) {
		ball.setId(null);
		sessionFactory.getCurrentSession().persist(ball);

	}

	@Override
	public Ball getBall(Ball ball) {
		return getBall(ball.getId());
	}

	@Override
	public Ball getBall(Long id) {
		return(Ball) sessionFactory.getCurrentSession().get(Ball.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ball> getBallsByColor(String color) {
		return sessionFactory.getCurrentSession().getNamedQuery("ball.getByColor").setParameter("color", color).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ball> getAllBalls() {
//		List<Ball> balls = new ArrayList<Ball>();
//		for(Object o : sessionFactory.getCurrentSession().getNamedQuery("ball.all").list()) {
//			balls.add((Ball) o);
//		}
		return sessionFactory.getCurrentSession().getNamedQuery("ball.all").list();
	//	return players;
	}

	@Override
	public void updateBall(Ball ball, String type) {
		Ball b = (Ball) sessionFactory.getCurrentSession().get(Ball.class, ball.getId());
		b.setType(type);
	}

	@Override
	public void deleteBall(Ball ball) {
		sessionFactory.getCurrentSession().delete(ball);

	}

	@Override
	public void deleteBall(Long id) {
		sessionFactory.getCurrentSession().delete(getBall(id));

	}

	@Override
	public void addManufacturer(Manufacturer manufacturer) {
		manufacturer.setId(null);
		sessionFactory.getCurrentSession().persist(manufacturer);

		
	}

	@Override
	public Manufacturer getManufacturer(Manufacturer manufacturer) {
		return getManufacturer(manufacturer.getId());
	}

	@Override
	public Manufacturer getManufacturer(Long id) {
		return(Manufacturer) sessionFactory.getCurrentSession().get(Manufacturer.class, id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getManufacturersByYOC(int YOC) {
		return sessionFactory.getCurrentSession().getNamedQuery("manufacturer.getByYOC").setParameter("YOC", YOC).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAllManufacturers() {
		return sessionFactory.getCurrentSession().getNamedQuery("manufacturer.all").list();

	}

	@Override
	public void updateManufacturer(Manufacturer manufacturer, String name) {
		Manufacturer m = (Manufacturer) sessionFactory.getCurrentSession().get(Manufacturer.class, manufacturer.getId());
		m.setName(name);	
	}

	@Override
	public void deleteManufacturer(Manufacturer manufacturer) {
		sessionFactory.getCurrentSession().delete(manufacturer);
		
	}

	@Override
	public void deleteManufacturer(Long id) {
		sessionFactory.getCurrentSession().delete(getManufacturer(id));
		
	}

	@Override
	public List<Ball> getManufacturerBalls(Manufacturer manufacturer) {
		List<Ball> balls = new ArrayList<Ball>(manufacturer.getBalls());
		return balls;
	}

	@Override
	public List<Ball> getManufacturerBalls(Long id) {
		List<Ball> balls = new ArrayList<Ball>(getManufacturer(id).getBalls());
		return balls;
	}


}
