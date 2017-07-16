package com.local.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.local.commons.Database;
import com.local.model.Actor;
import com.local.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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

	@Override
	public JasperPrint getJasperReport() throws FileNotFoundException, JRException {
		JasperPrint jasperPrint = null;
		/// SpringMavenMvc/src/main/resources/jasper/Blank_Letter_Landscape_Table_Based.jasper
		File file = ResourceUtils.getFile("classpath:/jasper/Blank_Letter_Landscape_Table_Based.jrxml");
		Connection con = Database.getConnection();
		FileInputStream inputStream = new FileInputStream(file);
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());

		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		HashMap<String, Object> params = new HashMap<>();
		jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		return jasperPrint;

	}
	
	

}
