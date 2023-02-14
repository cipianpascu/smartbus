package com.roka.smarthomeg4.business;

public class Security {

	
	private int SubnetID;
	private int DeviceID;
	private int ZoneID;
	private String ZoneNameOfSecurity;


	public Security() {
		// TODO Auto-generated constructor stub
	}


	public Security(int subnetID, int deviceID, int zoneID,
			String zoneNameOfSecurity) {
		super();
		SubnetID = subnetID;
		DeviceID = deviceID;
		ZoneID = zoneID;
		ZoneNameOfSecurity = zoneNameOfSecurity;
	}


	public int getSubnetID() {
		return SubnetID;
	}


	public void setSubnetID(int subnetID) {
		SubnetID = subnetID;
	}


	public int getDeviceID() {
		return DeviceID;
	}


	public void setDeviceID(int deviceID) {
		DeviceID = deviceID;
	}


	public int getZoneID() {
		return ZoneID;
	}


	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}


	public String getZoneNameOfSecurity() {
		return ZoneNameOfSecurity;
	}


	public void setZoneNameOfSecurity(String zoneNameOfSecurity) {
		ZoneNameOfSecurity = zoneNameOfSecurity;
	}
	
	
}
