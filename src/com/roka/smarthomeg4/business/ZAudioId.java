package com.roka.smarthomeg4.business;

public class ZAudioId {

	
	private int ID;
	private String ZoneName;
	private int SubnetID;
	private int DeviceID;
	
	public ZAudioId() {
		// TODO Auto-generated constructor stub
	}

	public ZAudioId(int iD, String zoneName, int subnetID, int deviceID) {
		super();
		ID = iD;
		ZoneName = zoneName;
		SubnetID = subnetID;
		DeviceID = deviceID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getZoneName() {
		return ZoneName;
	}

	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
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
	
	
	
	


}
