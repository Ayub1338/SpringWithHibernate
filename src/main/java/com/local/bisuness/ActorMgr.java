package com.local.bisuness;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.local.model.Actor;
import com.local.model.User;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;

public interface ActorMgr {

	public List<Actor> getAllActors();

	public Actor getActorById(String actorId);

	public Actor getjasperReport(String actorId);

	public Object createUser(User user);

	public byte[] getJasperReport() throws FileNotFoundException, SQLException;

	public byte[] generateDynamicJasperReport() throws ClassNotFoundException, ColumnBuilderException, SQLException, IllegalArgumentException, IllegalAccessException;

}
