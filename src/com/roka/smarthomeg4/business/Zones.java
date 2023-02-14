package com.roka.smarthomeg4.business;

import java.io.Serializable;

public class Zones implements Serializable{
	private int ZoneID;
	private String ZoneName;
	private int ZoneIconID;
	private int SequenceNo;
	
	public Zones() {
		// TODO Auto-generated constructor stub
	}

	public Zones(int zoneID, String zoneName, int zoneIconID, int sequenceNo) {
		super();
		ZoneID = zoneID;
		ZoneName = zoneName;
		ZoneIconID = zoneIconID;
		SequenceNo = sequenceNo;
	}

	public int getZoneID() {
		return ZoneID;
	}

	public void setZoneID(int zoneID) {
		ZoneID = zoneID;
	}

	public String getZoneName() {
		return ZoneName;
	}

	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
	}

	public int getZoneIconID() {
		return ZoneIconID;
	}

	public void setZoneIconID(int zoneIconID) {
		ZoneIconID = zoneIconID;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
	}
	
	
	
	
}
