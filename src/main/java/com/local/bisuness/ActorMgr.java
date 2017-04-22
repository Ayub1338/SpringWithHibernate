package com.local.bisuness;

import java.util.List;

import com.local.model.Actor;

public interface ActorMgr {

	public List<Actor> getAllActors();

	public Actor getActorById(String actorId);

}
