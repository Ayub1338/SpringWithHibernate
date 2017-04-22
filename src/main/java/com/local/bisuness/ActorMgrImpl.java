package com.local.bisuness;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.local.dao.ActorDao;
import com.local.model.Actor;

@Service("ActorMgr")
@Transactional
public class ActorMgrImpl implements ActorMgr{

	@Autowired
	ActorDao actorDao;
	
	
	@Override
	public List<Actor> getAllActors() {
		List<Actor> actors = new ArrayList<>();
		return actorDao.getAllActors();
	}


	@Override
	public Actor getActorById(String actorId) {
		
		return actorDao.getActorById(actorId);
	}

	
}
