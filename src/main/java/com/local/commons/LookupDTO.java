package com.local.commons;

import java.util.HashMap;
import java.util.Map;

public class LookupDTO {
	private Long id;
	private String code;
	private String description;
	private String displayMessage;
	Map<String, String> columnsMap = new HashMap<>();
	public LookupDTO(){
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LookupDTO(Long id, String code, String description, String displayMessage) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.displayMessage = displayMessage;
	}
	public Map<String, String> getColumnsMap() {
		columnsMap.put("id", "Primary Key");
		columnsMap.put("code", "Code");
		columnsMap.put("description", "Description");
		columnsMap.put("displayMessage", "Display Message");
		return columnsMap;
	}
	public void setColumnsMap(Map<String, String> columnsMap) {
		this.columnsMap = columnsMap;
	}
	
	
}
