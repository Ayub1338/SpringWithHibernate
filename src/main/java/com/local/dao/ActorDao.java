package com.local.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.local.model.Actor;
import com.local.model.User;

public interface ActorDao {
	
public List<Actor> getAllActors();

public Actor getActorById(String actorId);

public void getjasperReport(String actorId);

public void createUser(User user);
}
