package com.local.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.AbstractSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.local.model.Actor;
import com.local.model.User;

@Repository("ActorDao")
public class ActorDaoImpl implements ActorDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Bean
	public SessionFactory getsesssionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Actor> getAllActors() {
		Session session = sessionFactory.openSession();
		List<Actor> listOfActors = session.createQuery("from Actor").list();
		listOfActors.stream().forEach(actor -> {
			System.out.println(actor.toString());
		});

		return listOfActors;
	}

	@Override
	public Actor getActorById(String actorId) {
		Actor actor = null;

		Session session = sessionFactory.openSession();
		Integer actorID = new Integer(actorId);
		actor = (Actor) session.get(Actor.class, actorID);

		return actor;

	}

	@Override
	public void getjasperReport(String actorId) {

	}

	@Override
	public Object createUser(User user) {
		Transaction tx = null;
	 
		Session session = sessionFactory.openSession();
	session.beginTransaction();
		System.out.println(user.toString());
		try{
		session.save(user);
		System.out.println("Inserted Successfully");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		}
		catch(Exception e){
			return e.getMessage();
		}
		return user;
	

	}

}
