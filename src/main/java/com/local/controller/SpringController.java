package com.local.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.local.bisuness.ActorMgr;
import com.local.model.Actor;

@Controller
public class SpringController {
	
 	@Autowired(required = true)
	 private ActorMgr actorMgr;
	
 	//commented
 	
 	@RequestMapping(value = "/",method = RequestMethod.GET)
 	public String getIndexPage(){
 		ModelAndView modelAndView = new ModelAndView();
 		modelAndView.setViewName("index");
 		//return modelAndView;
 		return "index";
 	}
 	
 	
 	
 	
	@RequestMapping(value = "/getAllActors" , method = RequestMethod.GET)
	public  @ResponseBody Object  getAllActors(){
		System.out.println("in controller");
		return actorMgr.getAllActors();
		
		
	}
	
	@RequestMapping(value = "/getActorDetails/{actorId}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE) 
		public @ResponseBody Object getActorDetails(@PathVariable("actorId") String actorId ){
			System.out.println("in controller");
			Actor actor = null;
			try{
			  actor = actorMgr.getActorById(actorId);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return actor;
		}
	
}
