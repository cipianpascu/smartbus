package com.roka.smarthomeg4.business;

public class ZaudioInZone {

	private int ZoneID;
	private int SubnetID;
	private int DeviceID;
	
	public ZaudioInZone() {
		// TODO Auto-generated constructor stub
	}

	public ZaudioInZone(int zoneID, int subnetID, int deviceID) {
		super();
		ZoneID = zoneID;
		SubnetID = subnetID;
		DeviceID = deviceID;
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
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
