package com.local.bisuness;

import java.io.FileNotFoundException;
import java.util.List;

import com.local.model.Actor;
import com.local.model.User;

public interface ActorMgr {

	public List<Actor> getAllActors();

	public Actor getActorById(String actorId);

	public Actor getjasperReport(String actorId);

	public Object createUser(User user);

	public byte[] getJasperReport() throws FileNotFoundException;

}
