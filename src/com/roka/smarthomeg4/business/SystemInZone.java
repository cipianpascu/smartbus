package com.roka.smarthomeg4.business;

public class SystemInZone {

	private int ZoneID;
	private int SystemID;

	
	public SystemInZone() {
		// TODO Auto-generated constructor stub
	}


	public int getZoneID() {
		return ZoneID;
	}


	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}


	public int getSystemID() {
		return SystemID;
	}


	public void setSystemID(int systemID) {
		SystemID = systemID;
	}


	public SystemInZone(int zoneID, int systemID) {
		super();
		ZoneID = zoneID;
		SystemID = systemID;
	}
	
	
	
	
}
