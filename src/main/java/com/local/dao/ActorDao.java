package com.local.dao;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.local.model.Actor;
import com.local.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ActorDao {
	
public List<Actor> getAllActors();

public Actor getActorById(String actorId);

public void getjasperReport(String actorId);

public Object createUser(User user);

public JasperPrint getJasperReport() throws FileNotFoundException, JRException;
}
