package com.local.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.AbstractSessionFactoryBean;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.local.model.Actor;

@Repository("ActorDao")
public class ActorDaoImpl implements ActorDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

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
		Session session = sessionFactory.openSession();
		 Integer actorID = new Integer(actorId);
		Actor actor = (Actor) session.get(Actor.class, actorID);
		return actor;
		
	
		
	}




	@Bean
	public SessionFactory getsesssionFactory(){
		return  sessionFactory;
	}
}
