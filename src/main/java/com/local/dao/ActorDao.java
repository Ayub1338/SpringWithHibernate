package com.local.dao;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.local.model.Actor;

public interface ActorDao {
	
public List<Actor> getAllActors();

public Actor getActorById(String actorId);
}
