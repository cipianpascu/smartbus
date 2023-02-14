package com.roka.smarthomeg4.business;

public class SystemDefinition {
	private int SystemID;
	private String SystemName;
	
	public SystemDefinition() {
		// TODO Auto-generated constructor stub
	}

	public SystemDefinition(int systemID, String systemName) {
		super();
		SystemID = systemID;
		SystemName = systemName;
	}

	public int getSystemID() {
		return SystemID;
	}

	public void setSystemID(int systemID) {
		SystemID = systemID;
	}

	public String getSystemName() {
		return SystemName;
	}

	public void setSystemName(String systemName) {
		SystemName = systemName;
	}
	
	
	


}
